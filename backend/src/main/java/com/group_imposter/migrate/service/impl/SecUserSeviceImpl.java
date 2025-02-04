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
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class SecUserSeviceImpl implements SecUserService {
    String filePath = "src/main/java/com/group_imposter/migrate/data/user-security.txt";

    @Override
    public boolean doesUserIdExist(String userId) {
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String existingUserId = this.extractUserId(line);
//                if (existingUserId.equals(userId)) {
//                    return true;
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("Error when reading file");
//        }
//
//        return false;

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
        // validate USER-TYPE (refactor later)
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

//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
//            bw.write(secUserData.generateRecord());
//            bw.newLine();
//        } catch (IOException e) {
//            throw new RuntimeException("Unable to Add User...");
//        }

        FileAccessBase userSecFile = new FileAccessBase(filePath);
        userSecFile.open(FileOpenMode.OUT);

        userSecFile.write(SecUserData_Accessor.generateSecUserDataRecord(secUserData));

        userSecFile.close();

        ResponseObject responseObject = new ResponseObject();
        responseObject.setHttpStatus(HttpStatus.CREATED);
        responseObject.setMessage("User " + secUserData.getSecUsrId() + " has been added ...");
        responseObject.setData(secUserData);

        return responseObject;
    }
}
