package com.musinsa.menu.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseDTO<T> {
    private int status;
    private String message;
    private T data;


    public ResponseDTO createResponseDTO(T data){
        this.status = HttpStatus.OK.value();
        this.message = HttpStatus.OK.name();
        this.data = data;
        return this;
    }

}
