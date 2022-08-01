package com.musinsa.menu.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionEnum {

    USER_PARAM_ERROR(HttpStatus.BAD_REQUEST, "E001", "사용자 파라미터 오류"),
    BANNER_NOT_ALLOW(HttpStatus.BAD_REQUEST, "E002", "배너는 최상위 메뉴에만 달수있습니다.");


    private final HttpStatus status;
    private final String code;
    private String message;

    ExceptionEnum(HttpStatus status, String code) {
        this.status = status;
        this.code = code;
    }
    ExceptionEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
