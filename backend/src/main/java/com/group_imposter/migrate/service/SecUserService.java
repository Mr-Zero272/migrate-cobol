package com.group_imposter.migrate.service;

import com.group_imposter.migrate.dto.request.SecUserDataRequestDto;
import com.group_imposter.migrate.dto.response.ResponseObject;

public interface SecUserService {
  boolean doesUserIdExist(String userId);

  ResponseObject addNewSecUserData(SecUserDataRequestDto requestDto);

  ResponseObject updateSecUserData(SecUserDataRequestDto requestDto);
}
