package com.tiki.server.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 400 BAD REQUEST : 잘못된 요청 */
    UNCAUGHT_EXCEPTION(HttpStatus.BAD_REQUEST, "예상치 못한 오류가 발생했습니다."),
    INVALID_CHARACTER(HttpStatus.BAD_REQUEST, "한글,영어,숫자, 일부 특수문자 만 입력 가능합니다."),
    EXCEEDED_MAX_LENGTH(HttpStatus.BAD_REQUEST, "최대 길이를 초과했습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
