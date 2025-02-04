package com.group_imposter.migrate.exception;

import com.group_imposter.migrate.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingDefaultHandlerExceptionResolver(MethodArgumentNotValidException exception) {
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(400);
        apiResponse.setMessage(exception.getFieldError().getDefaultMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }
}
