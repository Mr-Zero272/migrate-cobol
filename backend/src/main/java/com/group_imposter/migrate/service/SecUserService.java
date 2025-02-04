package com.group_imposter.migrate.service;

import com.group_imposter.migrate.dto.request.SecUserDataRequestDto;
import com.group_imposter.migrate.dto.response.ApiResponse;
import com.group_imposter.migrate.model.SecUserData;

public interface SecUserService {
    boolean doesUserIdExist(String userId);
    ApiResponse<SecUserData> addNewSecUserData(SecUserDataRequestDto requestDto);
}
