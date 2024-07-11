package com.tiki.server.member.message;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 404 NOT_FOUND : 자원을 찾을 수 없음 */
    INVALID_MEMBER(NOT_FOUND, "유효하지 않은 회원입니다."),

    /* 409 CONFLICT : 이메일 중복*/
    CONFLICT_MEMBER(CONFLICT, "존재하는 이메일입니다."),

    /* 400 BAD REQUEST: 잘못된 요청*/
    UNMATCHED_PASSWORD(BAD_REQUEST, "비밀번호가 일치하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
