package com.tiki.server.auth.message;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 400 BAD REQUEST : 잘못된 요청 */
    UNCAUGHT_EXCEPTION(BAD_REQUEST, 40001, "예상치 못한 오류가 발생했습니다."),

    /* 401 UNAUTHORIZED : 인증 없음 */
    UNAUTHENTICATED(UNAUTHORIZED, 40101, "인증 과정 중 오류가 발생했습니다"),
    UNMATCHED_TOKEN(UNAUTHORIZED, 40102, "토큰이 일치하지 않습니다."),
    INVALID_JWT_TOKEN(UNAUTHORIZED, 40103, "잘못된 토큰 형식입니다."),
    EXPIRED_JWT_TOKEN(UNAUTHORIZED, 40104, "만료된 토큰입니다."),
    EMPTY_JWT(UNAUTHORIZED, 40105, "빈 토큰입니다."),

    /* 403 FORBIDDEN : 권한 없음 */
    UNAUTHORIZED_USER(FORBIDDEN, 40301, "권한이 없는 사용자입니다."),

    /* 500 INTERNAL_SERVER_ERROR : 서버 내부 오류 발생 */
    UNCAUGHT_SERVER_EXCEPTION(INTERNAL_SERVER_ERROR, 500, "서버 내부에서 오류가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
}
