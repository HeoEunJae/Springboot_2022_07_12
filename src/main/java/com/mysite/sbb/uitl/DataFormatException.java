package com.mysite.sbb.uitl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "entity not found")
public class DataFormatException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public DataFormatException(String message){
        super(message);
    }
}
