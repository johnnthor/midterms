package com.example.midtermsApi.Resources;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RuntimeException extends java.lang.RuntimeException {

    public RuntimeException(String message) {
        super(message);
    }

}
