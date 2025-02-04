package com.group_imposter.migrate.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseObject {
    private String status;
    private HttpStatus httpStatus;
    private String error;
    private String message;
    private Object data;
    
}
