package com.tiki.server.memberteammanager.message;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	/* 404 NOT_FOUND : 자원을 찾을 수 없음 */
	TEMP(NOT_FOUND, "컴파일 에러 방지용 에러입니다.");

	private final HttpStatus httpStatus;
	private final String message;
}
