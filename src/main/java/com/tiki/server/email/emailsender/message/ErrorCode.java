package com.tiki.server.email.emailsender.message;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    /* 500 INTERNAL_SERVER_ERROR : 서버 내부 오류 발생 */
    MESSAGE_HELPER_ERROR(INTERNAL_SERVER_ERROR,"메세지를 설정할 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
