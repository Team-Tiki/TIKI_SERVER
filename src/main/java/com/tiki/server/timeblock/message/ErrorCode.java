package com.tiki.server.timeblock.message;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	/* 400 BAD_REQUEST : 잘못된 요청 */
	INVALID_TYPE(BAD_REQUEST, "유효한 타입이 아닙니다."),
	INVALID_DOCUMENT_TAG(BAD_REQUEST, "유효한 파일이 아닙니다"),

	/* 404 NOT_FOUND : 자원을 찾을 수 없음 */
	INVALID_TIME_BLOCK(NOT_FOUND, "유효하지 않은 타임 블록입니다.");

	private final HttpStatus httpStatus;
	private final String message;
}
