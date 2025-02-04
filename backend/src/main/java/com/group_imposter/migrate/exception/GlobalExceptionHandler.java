package com.group_imposter.migrate.exception;

import com.group_imposter.migrate.dto.response.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ResponseObject> handlingDefaultHandlerExceptionResolver(MethodArgumentNotValidException exception) {
        ResponseObject responseObject = new ResponseObject();

        responseObject.setHttpStatus(HttpStatus.BAD_REQUEST);
        responseObject.setMessage(exception.getFieldError().getDefaultMessage());

        return ResponseEntity.badRequest().body(responseObject);
    }
}
