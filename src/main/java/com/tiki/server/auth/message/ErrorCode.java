package com.tiki.server.auth.message;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 401 UNAUTHORIZED : 인증 없음 */
    UNAUTHENTICATED_USER(UNAUTHORIZED, "잘못된 토큰 형식입니다."),
    INVALID_KEY(UNAUTHORIZED, "유효하지 않은 키입니다."),
    UNMATCHED_TOKEN(UNAUTHORIZED, "토큰이 일치하지 않습니다."),
    INVALID_JWT_TOKEN(UNAUTHORIZED, "잘못된 토큰 형식입니다."),
    EXPIRED_JWT_TOKEN(UNAUTHORIZED, "만료된 토큰입니다."),
    UNSUPPORTED_JWT_TOKEN(UNAUTHORIZED, "지원하지 않은 토큰입니다."),
    EMPTY_JWT(UNAUTHORIZED, "빈 토큰입니다."),

    /* 403 FORBIDDEN : 인가 없음 */
    UNAUTHORIZED_USER(FORBIDDEN, "권한이 없는 사용자입니다."),

    /* 500 INTERNAL_SERVER_ERROR : 서버 내부 오류입니다. */
    UNCAUGHT_EXCEPTION(INTERNAL_SERVER_ERROR, "서버 내부 오류입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
