package com.group_imposter.migrate.controller;

import com.group_imposter.migrate.dto.request.GetByIDUserDataRequestDto;
import com.group_imposter.migrate.dto.request.SecUserDataRequestDto;
import com.group_imposter.migrate.dto.response.ResponseObject;
import com.group_imposter.migrate.service.SecUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sec-user-data")
public class SecUserDataController {
  @Autowired
  private SecUserService secUserService;

  @PostMapping
  ResponseObject addNewSecUserData(@RequestBody @Valid SecUserDataRequestDto requestDto) {
    return secUserService.addNewSecUserData(requestDto);
  }


  @PutMapping
  public ResponseEntity<ResponseObject> updateSecUserData(@RequestBody @Valid SecUserDataRequestDto requestDto) {
    ResponseObject respone = secUserService.updateSecUserData(requestDto);
    return new ResponseEntity<>(respone, respone.getHttpStatus());
  }

  @PostMapping("/get-by-id")
  public ResponseEntity<ResponseObject> getByIdSecUserData(@RequestBody @Valid GetByIDUserDataRequestDto getByIdUser) {
    ResponseObject respone = secUserService.getByIdSecUserData(getByIdUser);
    return new ResponseEntity<>(respone, respone.getHttpStatus());
  }
}
