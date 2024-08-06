package com.tiki.server.auth.message;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 400 INTERNAL_SERVER_ERROR : 잘못된 요청입니다. */
    UNCAUGHT_EXCEPTION(BAD_REQUEST, "예상치 못한 오류입니다."),

    /* 401 UNAUTHORIZED : 인증 없음 */
    UNAUTHENTICATED(UNAUTHORIZED, "인증과정중 오류가 발생했습니다"),
    UNMATCHED_TOKEN(UNAUTHORIZED, "토큰이 일치하지 않습니다."),
    INVALID_JWT_TOKEN(UNAUTHORIZED, "잘못된 토큰 형식입니다."),
    EXPIRED_JWT_TOKEN(UNAUTHORIZED, "만료된 토큰입니다."),
    EMPTY_JWT(UNAUTHORIZED, "빈 토큰입니다."),

    /* 403 FORBIDDEN : 인가 없음 */
    UNAUTHORIZED_USER(FORBIDDEN, "권한이 없는 사용자입니다."),

    UNCAUGHT_SERVER_EXCEPTION(INTERNAL_SERVER_ERROR,"처리되지 않은 에러ㅜ(서버한테 물어보삼)");

    private final HttpStatus httpStatus;
    private final String message;
}
