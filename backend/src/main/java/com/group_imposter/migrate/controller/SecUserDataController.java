package com.group_imposter.migrate.controller;

import com.group_imposter.migrate.dto.request.SecUserDataRequestDto;
import com.group_imposter.migrate.dto.response.ApiResponse;
import com.group_imposter.migrate.model.SecUserData;
import com.group_imposter.migrate.service.SecUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sec-user-data")
public class SecUserDataController {
    @Autowired
    private SecUserService secUserService;

    @PostMapping
    ApiResponse<SecUserData> addNewSecUserData(@RequestBody @Valid SecUserDataRequestDto requestDto) {
        return secUserService.addNewSecUserData(requestDto);
    }
}
