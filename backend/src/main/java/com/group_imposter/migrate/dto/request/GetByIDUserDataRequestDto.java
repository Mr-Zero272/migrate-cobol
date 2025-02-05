package com.group_imposter.migrate.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetByIDUserDataRequestDto {
  @NotEmpty(message = "User ID can NOT be empty...")
  @Size(min = 8, max = 8, message = "User ID must be exactly 8 characters...")
  private String secUsrId;
}
