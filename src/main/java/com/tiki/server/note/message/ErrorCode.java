package com.tiki.server.note.message;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 404 NOT_FOUND : 자원을 찾을 수 없음 */
    INVALID_NOTE(NOT_FOUND, "유효하지 않은 노트입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
