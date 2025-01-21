package com.tiki.server.document.message;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
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

	/* 403 FORBIDDEN : 권한 없음 */
	INVALID_AUTHORIZATION(FORBIDDEN, "문서에 대한 권한이 없습니다."),

	/* 404 NOT_FOUND : 자원을 찾을 수 없음 */
	INVALID_DOCUMENT(NOT_FOUND, "유효하지 않은 문서입니다."),

	/* 409 CONFLICT : 중복된 자원 */
	DOCUMENT_NAME_DUPLICATE(CONFLICT, "중복된 파일 이름입니다.");

	private final HttpStatus httpStatus;
	private final String message;
}
