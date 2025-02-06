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

    while (!userSecFile.isEOF()) {
      userSecFile.readLine();

      if (SecUserData_Accessor.extractUserId(userSecFile.getCurrentLine()).equals(userId)) {
        return true;
      }
    }

    userSecFile.close();
    return false;
  }

  @Override
  public ResponseObject getByIdSecUserData(GetByIDUserDataRequestDto getByIDUserDataRequestDto) {
    FileAccessBase userSecFile = new FileAccessBase(filePath);
    userSecFile.open(FileOpenMode.IN);

    while (!userSecFile.isEOF()) {
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
      return ResponseObject.builder().httpStatus(HttpStatus.BAD_REQUEST).message("User type must be A or U").build();
    }

    SecUserData secUserData = new SecUserData();

    secUserData.setSecUsrId(requestDto.getSecUsrId().toUpperCase());
    secUserData.setSecUsrFname(FieldFormat.format(20, requestDto.getSecUsrFname()).toUpperCase());
    secUserData.setSecUsrLname(FieldFormat.format(20, requestDto.getSecUsrLname()).toUpperCase());
    secUserData.setSecUsrPwd(requestDto.getSecUsrPwd().toUpperCase());
    secUserData.setSecUsrType(requestDto.getSecUsrType().toUpperCase());

    if (doesUserIdExist(secUserData.getSecUsrId())) {
      return ResponseObject.builder().httpStatus(HttpStatus.BAD_REQUEST).message("User ID already exist...").build();
    }

    FileAccessBase userSecFile = new FileAccessBase(filePath);
    userSecFile.open(FileOpenMode.OUT);

    // get sercurity user data type sting line and write to file
    userSecFile.write(SecUserData_Accessor.getSecUserData(secUserData));

    userSecFile.close();

    return ResponseObject.builder().httpStatus(HttpStatus.CREATED).message("User " + secUserData.getSecUsrId() + " has been added ...").data(secUserData).build();
  }


  @Override
  public ResponseObject updateSecUserData(SecUserDataRequestDto requestDto) {
    if (!requestDto.getSecUsrType().equalsIgnoreCase("A") && !requestDto.getSecUsrType().equalsIgnoreCase("U")) {
      return ResponseObject.builder().httpStatus(HttpStatus.BAD_REQUEST).message("User type must be A or U").build();
    }

    FileAccessBase userSecFile = new FileAccessBase(filePath);
    userSecFile.open(FileOpenMode.IN);

    StringBuilder fileContent = new StringBuilder();
    boolean isHaveUserUpdate = false;

    SecUserData previousSecUserDataUpdate = new SecUserData();

    while (!userSecFile.isEOF()) {
      userSecFile.readLine();
      String currentLine = userSecFile.getCurrentLine();
      if (currentLine == null || currentLine.isEmpty()) continue;
      String secUserId = SecUserData_Accessor.extractUserId(currentLine);
      if (secUserId.equals(requestDto.getSecUsrId())) {
        SecUserData_Accessor.setSecUserData(previousSecUserDataUpdate, currentLine);
        isHaveUserUpdate = true;
      } else {
        fileContent.append(currentLine).append("\n");
      }
    }
    userSecFile.close();

    if (!isHaveUserUpdate) {
      return ResponseObject.builder()
              .status("error")
              .httpStatus(HttpStatus.NOT_FOUND)
              .message("User ID NOT found...")
              .build();
    }

    SecUserData newSecUserDataUpdate = new SecUserData();
    newSecUserDataUpdate.setSecUsrId(requestDto.getSecUsrId().toUpperCase());
    newSecUserDataUpdate.setSecUsrFname(FieldFormat.format(20, requestDto.getSecUsrFname()).toUpperCase());
    newSecUserDataUpdate.setSecUsrLname(FieldFormat.format(20, requestDto.getSecUsrLname()).toUpperCase());
    newSecUserDataUpdate.setSecUsrPwd(requestDto.getSecUsrPwd().toUpperCase());
    newSecUserDataUpdate.setSecUsrType(requestDto.getSecUsrType().toUpperCase());

    if (!isUserDataModified(previousSecUserDataUpdate, newSecUserDataUpdate)) {
      return ResponseObject.builder()
              .status("error")
              .httpStatus(HttpStatus.BAD_REQUEST)
              .message("Please modify to update ...")
              .build();
    }

    fileContent.append(SecUserData_Accessor.getSecUserData(newSecUserDataUpdate));
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
            .message("Updated user successfully")
            .build();
  }

  public boolean isUserDataModified(SecUserData previousSecUserDataUpdate, SecUserData newSecuserDataUpdate) {
    boolean usrModifiedFlg = false;

    if (!previousSecUserDataUpdate.getSecUsrFname().equals(newSecuserDataUpdate.getSecUsrFname())) {
      usrModifiedFlg = true;
    }

    if (!previousSecUserDataUpdate.getSecUsrLname().equals(newSecuserDataUpdate.getSecUsrLname())) {
      usrModifiedFlg = true;
    }

    if (!previousSecUserDataUpdate.getSecUsrPwd().equals(newSecuserDataUpdate.getSecUsrPwd())) {
      usrModifiedFlg = true;
    }

    if (!previousSecUserDataUpdate.getSecUsrType().equals(newSecuserDataUpdate.getSecUsrType())) {
      usrModifiedFlg = true;
    }

    return  usrModifiedFlg;
  }

}




