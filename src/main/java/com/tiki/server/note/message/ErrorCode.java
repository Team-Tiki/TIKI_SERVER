package com.tiki.server.note.message;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 400 BAD REQUEST : 잘못된 요청 */
    TITLE_IS_EMPTY(BAD_REQUEST, "제목은 필수 입력값 입니다."),
    TITLE_LENGTH_OVER(BAD_REQUEST, "제목은 100자를 넘길 수 없습니다."),

    /* 404 NOT_FOUND : 자원을 찾을 수 없음 */
    INVALID_NOTE(NOT_FOUND, "유효하지 않은 노트입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
