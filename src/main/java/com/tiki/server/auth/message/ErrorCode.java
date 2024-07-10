package com.tiki.server.auth.message;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.HttpStatus.FORBIDDEN;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 401 UNAUTHORIZED : 인증 없음 */
    UNAUTHENTICATED_USER(UNAUTHORIZED, "인증되지 않은 사용자입니다."),
    INVALID_KEY(UNAUTHORIZED, "유효하지 않은 키입니다."),
    /* 403 FORBIDDEN : 인가 없음 */
    UNAUTHORIZED_USER(FORBIDDEN, "권한이 없는 사용자입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
