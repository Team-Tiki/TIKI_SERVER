package com.tiki.server.emailverification.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 403 FORBIDDEN : 권한 없음 */
    INVALID_MATCHED(FORBIDDEN, "인증 정보가 일치하지 않습니다."),

    /* 404 NOT_FOUND : 자원을 찾을 수 없음 */
    INVALID_REQUEST(NOT_FOUND, "인증 정보가 존재하지 않습니다."),

    /* 500 INTERNAL_SERVER_ERROR : 서버 내부 오류 발생 */
    MESSAGE_HELPER_ERROR(INTERNAL_SERVER_ERROR,"메세지를 설정할 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
