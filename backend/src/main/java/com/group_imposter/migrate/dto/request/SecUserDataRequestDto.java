package com.group_imposter.migrate.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SecUserDataRequestDto {
    @NotEmpty(message = "User ID can NOT be empty...")
    @Size(min = 8, max = 8, message = "User ID must be exactly 8 characters...")
    private String secUsrId;

    @NotEmpty(message = "First Name can NOT be empty...")
    private String secUsrFname;

    @NotEmpty(message = "Last Name can NOT be empty...")
    private String secUsrLname;

    @NotEmpty(message = "Password can NOT be empty...")
    @Size(min = 8, max = 8, message = "Password must be exactly 8 characters...")
    private String secUsrPwd;

    @NotEmpty(message = "User Type can NOT be empty...")
    private String secUsrType;

    private String secUsrFiller;
}