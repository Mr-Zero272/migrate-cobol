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
    String filePath = "D:\\Master\\migrate-cobol\\backend\\src\\main\\java\\com\\group_imposter\\migrate\\data\\user-security.txt";
    @Override
    public ResponseObject getPageSecUserDate(int page) {
        List<SecUserData> secUserDataList = new ArrayList<>();

        FileAccessBase userSecFile = new FileAccessBase(filePath);
        userSecFile.open(FileOpenMode.IN);

        boolean isEOF = false;

        int start = 10* (page - 1);
        int end = page*10;

        int line = 1;
        while (!isEOF) {
            userSecFile.readLine();

            if(line >= start && line <= end) {
                SecUserData secUserData = new SecUserData();
                SecUserData_Accessor.setSecUserData(secUserData, userSecFile.getCurrentLine());
                secUserDataList.add(secUserData);
            }
            line++;

            isEOF = userSecFile.isEOF();
        }

        userSecFile.close();
        if(secUserDataList.isEmpty()){
            return ResponseObject.builder()
                    .status("error")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .data(secUserDataList)
                    .message("You are already at the bottom of the page...")
                    .build();
        }
        return ResponseObject.builder()
                .status("success")
                .httpStatus(HttpStatus.OK)
                .data(secUserDataList)
                .build();
    }


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
        // lưu trữ ngoại trừ dòng chứa secUsrId bị xóa
        List<String> lines = new ArrayList<>();
        boolean userFound = false;

        // isEOF trả về true khi đọc hết file
        while (!fileAccess.isEOF()) {
            fileAccess.readLine();
//            Lấy nội dung dòng vừa đọc và lưu vào biến currentLine.
            String currentLine = fileAccess.getCurrentLine();
            if (currentLine != null) {
//                Nếu dòng KHÔNG chứa secUsrId, thêm vào danh sách lines (giữ lại).
//
                if (!currentLine.startsWith(secUsrId)) {
                    lines.add(currentLine);
                } else {
                    userFound = true;
                }
            }
        }
        fileAccess.close();

        if (!userFound) {
            return ResponseObject.builder()
                    .status("error")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message("User ID NOT found...")
                    .build();
        }

        File file = new File(filePath);
        if (!file.exists()) {
            return ResponseObject.builder()
                    .status("error")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message("File không tồn tại.")
                    .build();
        }


//        Một tiến trình khác hoặc chính JVM vẫn đang giữ file, khiến nó không thể bị xóa ngay lập tức.
//          Cần thử xóa nhiều lần và chờ một khoảng thời gian ngắn để hệ thống giải phóng file.
        boolean deleted = false;
        int retryCount = 0;
        while (!deleted && retryCount < 5) {
//            giúp yêu cầu dọn dẹp bộ nhớ, giúp xóa file dễ dàng hơn.
            System.gc(); // Giải phóng bộ nhớ để tránh khóa file
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
                    .message("Không thể xóa file cũ sau nhiều lần thử.")
                    .build();
        }

        try {
//            Sau khi xóa user khỏi danh sách, hệ thống thử tạo lại file nếu file đã bị xóa trước đó.
            if (!file.createNewFile()) {
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
//        Nếu danh sách lines rỗng, nghĩa là không còn user nào trong file.
        if (lines.isEmpty()) {
            return ResponseObject.builder()
                    .status("success")
                    .httpStatus(HttpStatus.OK)
                    .message("User với secUsrId: " + secUsrId + " đã bị xóa. (File trống)")
                    .build();
        }
//        Mở file để ghi lại dữ liệu còn lại
        fileAccess = new FileAccessBase(filePath);
        String openStatus = fileAccess.open(FileOpenMode.OUT);
        if (!FileStatusConstant.SUCCESS.equals(openStatus)) {
            return ResponseObject.builder()
                    .status("error")
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Không thể mở file để ghi.")
                    .build();
        }

        for (String line : lines) {
//            Nếu dòng không rỗng, ghi vào file.
            if (line != null && !line.trim().isEmpty()) {
                fileAccess.write(line);
            } else {
                System.out.println("Bỏ qua dòng rỗng hoặc null...");
            }
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
