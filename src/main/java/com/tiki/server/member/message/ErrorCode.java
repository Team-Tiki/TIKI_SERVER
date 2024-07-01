package com.tiki.server.member.message;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	/* 404 NOT_FOUND : 자원을 찾을 수 없음 */
	INVALID_MEMBER(NOT_FOUND, "유효하지 않은 회원입니다.");

	private final HttpStatus httpStatus;
	private final String message;
}
