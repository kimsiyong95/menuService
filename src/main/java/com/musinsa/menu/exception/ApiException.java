package com.musinsa.menu.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException{

    private ExceptionEnum error;

    public ApiException(ExceptionEnum e){
        super(e.getMessage());
        this.error = e;
    }
}
