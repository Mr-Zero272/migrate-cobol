package com.group_imposter.migrate.service.impl;

import com.group_imposter.migrate.accessor.SecUserData_Accessor;
import com.group_imposter.migrate.dto.request.SecUserDataRequestDto;
import com.group_imposter.migrate.dto.response.ResponseObject;
import com.group_imposter.migrate.file.FileAccessBase;
import com.group_imposter.migrate.file.FileOpenMode;
import com.group_imposter.migrate.model.SecUserData;
import com.group_imposter.migrate.service.SecUserService;
import com.group_imposter.migrate.util.FieldFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class SecUserSeviceImpl implements SecUserService {
    String filePath = "E:\\cobol_fosft_training\\migrate-cobol\\backend\\src\\main\\java\\com\\group_imposter\\migrate\\data\\user-security.txt";

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
}
