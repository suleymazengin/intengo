package com.intengo.intengo.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RequestException extends RuntimeException {

    private String msg;
}



