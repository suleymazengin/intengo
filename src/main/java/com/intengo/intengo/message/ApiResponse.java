package com.intengo.intengo.message;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Data
@Getter
@Setter
public class ApiResponse {

    private int status;
    private String message;
    private Object result;

    public ApiResponse(HttpStatus status, String message, Object result) {
        this.status = status.value();
        this.message = message;
        this.result = result;
    }

    public ApiResponse(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }




}
