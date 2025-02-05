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
public class SecUserDataController  {
    @Autowired
    private SecUserService secUserService;

    @GetMapping("/{page}")
    public ResponseEntity<ResponseObject> getPageSecUserData(@PathVariable int page){
        ResponseObject respone = secUserService.getPageSecUserDate(page);
        return new ResponseEntity<>(respone, respone.getHttpStatus());
    }

    @PostMapping("/get-by-id")
    public ResponseEntity<ResponseObject> getByIdSecUserData(@RequestBody @Valid GetByIDUserDataRequestDto getByIdUser) {
        ResponseObject respone = secUserService.getByIdSecUserData(getByIdUser);
        return new ResponseEntity<>(respone, respone.getHttpStatus());
    }

    @DeleteMapping("/{secUsrId}")
    public ResponseEntity<ResponseObject> deleteUser(@PathVariable String secUsrId) {
        ResponseObject response = secUserService.deleteUser(secUsrId);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping
    ResponseEntity<ResponseObject> addNewSecUserData(@RequestBody @Valid SecUserDataRequestDto requestDto) {
        ResponseObject responseObject = secUserService.addNewSecUserData(requestDto);
        return new ResponseEntity<>(responseObject, responseObject.getHttpStatus());
    }

    @PutMapping
    public ResponseEntity<ResponseObject> updateSecUserData(@RequestBody @Valid SecUserDataRequestDto requestDto) {
        ResponseObject respone = secUserService.updateSecUserData(requestDto);
        return new ResponseEntity<>(respone, respone.getHttpStatus());
    }
}
