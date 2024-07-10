package com.tiki.server.external.message;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	/* 400 BAD_REQUEST : 잘못된 요청 */
	INVALID_FILE_SIZE(BAD_REQUEST, "파일의 용량은 30MB를 초과할 수 없습니다.");

	private final HttpStatus httpStatus;
	private final String message;
}
