package com.group_imposter.migrate.controller;

import com.group_imposter.migrate.dto.request.SecUserDataRequestDto;
import com.group_imposter.migrate.dto.response.ResponseObject;
import com.group_imposter.migrate.service.SecUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<ResponseObject> addNewSecUserData(@RequestBody @Valid SecUserDataRequestDto requestDto) {
        ResponseObject responseObject = secUserService.addNewSecUserData(requestDto);
        return new ResponseEntity<>(responseObject, responseObject.getHttpStatus());
    }
}
