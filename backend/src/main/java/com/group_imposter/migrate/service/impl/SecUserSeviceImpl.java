package com.group_imposter.migrate.service.impl;


import com.group_imposter.migrate.accessor.SecUserData_Accessor;
import com.group_imposter.migrate.dto.request.GetByIDUserDataRequestDto;
import com.group_imposter.migrate.dto.request.SecUserDataRequestDto;
import com.group_imposter.migrate.dto.response.ResponseObject;
import com.group_imposter.migrate.file.FileAccessBase;
import com.group_imposter.migrate.file.FileOpenMode;
import com.group_imposter.migrate.file.FileStatusConstant;
import com.group_imposter.migrate.model.SecUserData;
import com.group_imposter.migrate.service.SecUserService;
import com.group_imposter.migrate.util.FieldFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SecUserSeviceImpl  implements SecUserService {
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
    public ResponseObject deleteUser(String secUsrId) {
        FileAccessBase fileAccess = new FileAccessBase(filePath);
        String status = fileAccess.open(FileOpenMode.IN);

        if (!FileStatusConstant.SUCCESS.equals(status)) {
            return ResponseObject.builder()
                    .status("error")
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Lỗi mở file!")
                    .build();
        }

        List<String> lines = new ArrayList<>();
        int originalLineCount = 0;
        boolean userFound = false;

        // Đọc file và lọc dữ liệu
        while (!fileAccess.isEOF()) {
            fileAccess.readLine();
            String currentLine = fileAccess.getCurrentLine();
            if (currentLine != null) {
                originalLineCount++;
                if (!currentLine.startsWith(secUsrId)) {
                    lines.add(currentLine);  // Giữ user lại nếu không trùng secUsrId
                } else {
                    userFound = true;  // Đánh dấu user đã được tìm thấy
                }
            }
        }
        fileAccess.close();

        // Kiểm tra xem user có tồn tại không
        if (!userFound) {
            return ResponseObject.builder()
                    .status("error")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message("User ID NOT found...")
                    .build();
        }

        // Xóa file hiện tại và tạo lại một file trống
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();  // Xóa file cũ
        }

        // Tạo lại file mới
        try {
            if (file.createNewFile()) {
                System.out.println("File đã được tạo lại.");
            } else {
                return ResponseObject.builder()
                        .status("error")
                        .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message("Không thể tạo lại file.")
                        .build();
            }
        } catch (IOException e) {
            return ResponseObject.builder()
                    .status("error")
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Lỗi khi tạo lại file: " + e.getMessage())
                    .build();
        }

        // Ghi lại file với danh sách user còn lại
        fileAccess = new FileAccessBase(filePath);
        fileAccess.open(FileOpenMode.OUT);

        for (String line : lines) {
            fileAccess.write(line);  // Ghi lại các dòng còn lại
        }
        fileAccess.close();

        return ResponseObject.builder()
                .status("success")
                .httpStatus(HttpStatus.OK)
                .message("User với secUsrId: " + secUsrId + " đã bị xóa.")
                .build();
    }

    @Override
    public ResponseObject updateSecUserData(SecUserDataRequestDto requestDto) {
        if (!requestDto.getSecUsrType().equalsIgnoreCase("A") && !requestDto.getSecUsrType().equalsIgnoreCase("U")) {
            throw new RuntimeException("User type must be A or U");
        }

        FileAccessBase userSecFile = new FileAccessBase(filePath);
        userSecFile.open(FileOpenMode.IN);

        StringBuilder fileContent = new StringBuilder();
        boolean haveUserUpdated = false;

        while (!userSecFile.isEOF()) {
            userSecFile.readLine();
            String currentLine = userSecFile.getCurrentLine();
            String secUserId = SecUserData_Accessor.extractUserId(currentLine);
            if (secUserId.equals(requestDto.getSecUsrId())) {
                haveUserUpdated = true;
            } else {
                fileContent.append(currentLine).append("\n");
            }
        }
        userSecFile.close();

        if (!haveUserUpdated) {
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

        fileContent.append(SecUserData_Accessor.getSecUserData(updatedUserData)).append("\n");

        File file = new File(filePath);

        if (file.exists()) {
            if (file.delete()) {
                FileAccessBase userSecFileOut = new FileAccessBase(filePath);
                userSecFileOut.createNewFile();
                userSecFileOut.open(FileOpenMode.OUT);
                userSecFileOut.write(fileContent.toString().trim());
                userSecFileOut.close();
            }
        }

        return ResponseObject.builder()
                .status("success")
                .httpStatus(HttpStatus.OK)
                .message("Updated user successfully")
                .build();
    }

}
