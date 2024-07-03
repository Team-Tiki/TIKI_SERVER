package com.tiki.server.auth.message;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	/* 401 UNAUTHORIZED : 권한 없음 */
	INVALID_TOKEN(UNAUTHORIZED, "유효하지 않은 토큰입니다.");

	private final HttpStatus httpStatus;
	private final String message;
}
