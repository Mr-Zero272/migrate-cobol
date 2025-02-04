package com.group_imposter.migrate.service.impl;

import com.group_imposter.migrate.dto.request.SecUserDataRequestDto;
import com.group_imposter.migrate.dto.response.ResponseObject;
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
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String existingUserId = this.extractUserId(line);
                if (existingUserId.equals(userId)) {
                    return true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error when reading file");
        }

        return false;
    }

    @Override
    public ResponseObject addNewSecUserData(SecUserDataRequestDto requestDto) {
        // validate USER-TYPE (refactor later)
        if (!requestDto.getSecUsrType().equalsIgnoreCase("A") && !requestDto.getSecUsrType().equalsIgnoreCase("U")) {
            throw new RuntimeException("User type must be A or U");
        }

        SecUserData secUserData = new SecUserData();

        secUserData.setSecUsrId(requestDto.getSecUsrId());
        secUserData.setSecUsrFname(FieldFormat.format(20, requestDto.getSecUsrFname()));
        secUserData.setSecUsrLname(FieldFormat.format(20, requestDto.getSecUsrLname()));
        secUserData.setSecUsrPwd(requestDto.getSecUsrPwd());
        secUserData.setSecUsrType(requestDto.getSecUsrType());

        if (doesUserIdExist(secUserData.getSecUsrId())) {
            throw new RuntimeException("User ID already exist...");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(secUserData.generateRecord());
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Unable to Add User...");
        }

        ResponseObject responseObject = new ResponseObject();
        responseObject.setHttpStatus(HttpStatus.CREATED);
        responseObject.setMessage("User " + secUserData.getSecUsrId() + " has been added ...");
        responseObject.setData(secUserData);

        return responseObject;
    }

    // Trích xuất User ID
    private String extractUserId(String line) {
        return line.substring(0, 8).trim(); // SEC-USR-ID (8 ký tự)
    }

    // Trích xuất First Name
    private String extractFirstName(String line) {
        return line.substring(8, 28).trim(); // SEC-USR-FNAME (20 ký tự)
    }

    // Trích xuất Last Name
    private String extractLastName(String line) {
        return line.substring(28, 48).trim(); // SEC-USR-LNAME (20 ký tự)
    }

    // Trích xuất Password
    private String extractPassword(String line) {
        return line.substring(48, 56).trim();  // SEC-USR-PWD (8 ký tự)
    }

    // Trích xuất User Type
    private String extractUserType(String line) {
        return line.substring(56, 57).trim(); // SEC-USR-TYPE (1 ký tự)
    }
}
