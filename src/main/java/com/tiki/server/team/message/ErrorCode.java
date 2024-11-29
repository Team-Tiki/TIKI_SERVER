package com.tiki.server.team.message;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	/* 400 BAD_REQUEST : 잘못된 요청 */
	TOO_HIGH_AUTHORIZATION(BAD_REQUEST, "어드민은 진행할 수 없습니다."),

	/* 403 FORBIDDEN : 권한 없음 */
	INVALID_AUTHORIZATION_DELETE(FORBIDDEN, "권한이 없습니다."),

	/* 404 NOT_FOUND : 자원을 찾을 수 없음 */
	INVALID_TEAM(NOT_FOUND, "유효하지 않은 단체입니다.");

	private final HttpStatus httpStatus;
	private final String message;
}
