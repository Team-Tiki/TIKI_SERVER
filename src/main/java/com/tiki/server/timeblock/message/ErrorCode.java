package com.tiki.server.timeblock.message;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	/* 400 BAD_REQUEST : 잘못된 요청 */
	INVALID_TYPE(BAD_REQUEST, "유효한 타입이 아닙니다."),

	/* 403 FORBIDDEN : 권한 없음 */
	INVALID_AUTHORIZATION(FORBIDDEN, "타임블록에 대한 권한이 없습니다.");

	private final HttpStatus httpStatus;
	private final String message;
}
