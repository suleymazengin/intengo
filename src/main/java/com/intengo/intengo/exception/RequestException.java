package com.intengo.intengo.exception;


import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Data
public class RequestException extends RuntimeException {

    private String msg;
}



