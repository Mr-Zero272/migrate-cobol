package com.group_imposter.migrate.service.impl;

import com.group_imposter.migrate.accessor.SecUserData_Accessor;
import com.group_imposter.migrate.dto.request.GetByIDUserDataRequestDto;
import com.group_imposter.migrate.dto.request.SecUserDataRequestDto;
import com.group_imposter.migrate.dto.response.ResponseObject;
import com.group_imposter.migrate.file.FileAccessBase;
import com.group_imposter.migrate.file.FileOpenMode;
import com.group_imposter.migrate.model.SecUserData;
import com.group_imposter.migrate.service.SecUserService;
import com.group_imposter.migrate.util.FieldFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class SecUserSeviceImpl implements SecUserService {
  String filePath = "src/main/java/com/group_imposter/migrate/data/user-security.txt";

  @Override
  public boolean doesUserIdExist(String userId) {

    FileAccessBase userSecFile = new FileAccessBase(filePath);
    userSecFile.open(FileOpenMode.IN);

    boolean isEOF = false;

    while (!isEOF) {
      userSecFile.readLine();

      if (SecUserData_Accessor.extractUserId(userSecFile.getCurrentLine()).equals(userId)) {
        return true;
      }

      isEOF = userSecFile.isEOF();
    }

    userSecFile.close();
    return false;

  }


  @Override
  public ResponseObject getByIdSecUserData(GetByIDUserDataRequestDto getByIDUserDataRequestDto) {
    FileAccessBase userSecFile = new FileAccessBase(filePath);
    userSecFile.open(FileOpenMode.IN);
    boolean isEOF = false;

    while (!isEOF) {
      userSecFile.readLine();
      if (SecUserData_Accessor.extractUserId(userSecFile.getCurrentLine()).equals(getByIDUserDataRequestDto.getSecUsrId())) {
        SecUserData secUserData = new SecUserData();
        SecUserData_Accessor.setSecUserData(secUserData, userSecFile.getCurrentLine());
        return ResponseObject.builder()
                .status("success")
                .httpStatus(HttpStatus.OK)
                .message("Press PF5 key to save your updates ...")
                .data(secUserData)
                .build();
      }
      isEOF = userSecFile.isEOF();
    }

    return ResponseObject.builder()
            .status("error")
            .httpStatus(HttpStatus.NOT_FOUND)
            .message("User ID NOT found...")
            .build();
  }


  @Override
  public ResponseObject addNewSecUserData(SecUserDataRequestDto requestDto) {
    if (!requestDto.getSecUsrType().equalsIgnoreCase("A") && !requestDto.getSecUsrType().equalsIgnoreCase("U")) {
      throw new RuntimeException("User type must be A or U");
    }

    SecUserData secUserData = new SecUserData();

    secUserData.setSecUsrId(requestDto.getSecUsrId().toUpperCase());
    secUserData.setSecUsrFname(FieldFormat.format(20, requestDto.getSecUsrFname()).toUpperCase());
    secUserData.setSecUsrLname(FieldFormat.format(20, requestDto.getSecUsrLname()).toUpperCase());
    secUserData.setSecUsrPwd(requestDto.getSecUsrPwd().toUpperCase());
    secUserData.setSecUsrType(requestDto.getSecUsrType().toUpperCase());

    if (doesUserIdExist(secUserData.getSecUsrId())) {
      throw new RuntimeException("User ID already exist...");
    }

    FileAccessBase userSecFile = new FileAccessBase(filePath);
    userSecFile.open(FileOpenMode.OUT);
    userSecFile.write(SecUserData_Accessor.getSecUserData(secUserData));
    userSecFile.close();
    ResponseObject responseObject = new ResponseObject();
    responseObject.setHttpStatus(HttpStatus.CREATED);
    responseObject.setMessage("User " + secUserData.getSecUsrId() + " has been added ...");
    responseObject.setData(secUserData);

    return responseObject;
  }


  @Override
  public ResponseObject updateSecUserData(SecUserDataRequestDto requestDto) {
    if (!requestDto.getSecUsrType().equalsIgnoreCase("A") && !requestDto.getSecUsrType().equalsIgnoreCase("U")) {
      throw new RuntimeException("User type must be A or U");
    }

    FileAccessBase userSecFile = new FileAccessBase(filePath);
    userSecFile.open(FileOpenMode.IN);

    StringBuilder fileContent = new StringBuilder();
    boolean userUpdated = false;

    while (!userSecFile.isEOF()) {
      userSecFile.readLine();
      String currentLine = userSecFile.getCurrentLine();
      if (currentLine == null || currentLine.isEmpty()) continue;
      String existingUserId = SecUserData_Accessor.extractUserId(currentLine);
      if (existingUserId.equals(requestDto.getSecUsrId())) {
        userUpdated = true;
      } else {
        fileContent.append(currentLine).append("\n");
      }
    }
    userSecFile.close();

    if (!userUpdated) {
      return ResponseObject.builder()
              .status("error")
              .httpStatus(HttpStatus.NOT_FOUND)
              .message("User ID NOT found...")
              .build();
    }

    SecUserData updatedUserData = new SecUserData();
    updatedUserData.setSecUsrId(requestDto.getSecUsrId().toUpperCase());
    updatedUserData.setSecUsrFname(FieldFormat.format(20, requestDto.getSecUsrFname()).toUpperCase());
    updatedUserData.setSecUsrLname(FieldFormat.format(20, requestDto.getSecUsrLname()).toUpperCase());
    updatedUserData.setSecUsrPwd(requestDto.getSecUsrPwd().toUpperCase());
    updatedUserData.setSecUsrType(requestDto.getSecUsrType().toUpperCase());


    fileContent.append(SecUserData_Accessor.getSecUserData(updatedUserData));
    File file = new File(filePath);
    boolean deleted = false;
    int retryCount = 0;
    while (!deleted && retryCount < 5) {
      System.gc();
      deleted = file.delete();
      if (!deleted) {
        try {
          Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }
      }
      retryCount++;
    }

    if (!deleted) {
      return ResponseObject.builder()
              .status("error")
              .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
              .message("Cannot delete the old file after many trials")
              .build();
    }

    FileAccessBase userSecFileOut = new FileAccessBase(filePath);
    userSecFileOut.open(FileOpenMode.OUT);
    userSecFileOut.write(fileContent.toString().trim());
    userSecFileOut.close();

    return ResponseObject.builder()
            .status("success")
            .httpStatus(HttpStatus.OK)
            .message("User " + updatedUserData.getSecUsrId() + " has been updated ...")
            .build();


  }
}



