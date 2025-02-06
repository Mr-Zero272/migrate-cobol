package com.group_imposter.migrate.service;

import com.group_imposter.migrate.dto.request.GetByIDUserDataRequestDto;
import com.group_imposter.migrate.dto.request.SecUserDataRequestDto;
import com.group_imposter.migrate.dto.response.ResponseObject;
import com.group_imposter.migrate.model.SecUserData;

public interface SecUserService {
    boolean doesUserIdExist(String userId);
    ResponseObject addNewSecUserData(SecUserDataRequestDto requestDto);
    ResponseObject deleteUser(String secUsrId);
    ResponseObject updateSecUserData(SecUserDataRequestDto requestDto);
    ResponseObject getByIdSecUserData(GetByIDUserDataRequestDto getByIdUser);
    ResponseObject getPageSecUserDate(int page);
}

