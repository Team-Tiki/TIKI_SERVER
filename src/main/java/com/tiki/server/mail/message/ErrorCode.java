package com.tiki.server.mail.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	/* 403 BAD REQUEST: 인증 거부*/
	INVALID_MATCHED(FORBIDDEN,"인증 정보가 일치하지 않습니다."),

	/* 404 NOT FOUND: 요청 리소스를 찾을 수 없음*/
	INVALID_REQUEST(NOT_FOUND,"인증 정보가 존재하지 않습니다.")
	;

	private final HttpStatus httpStatus;
	private final String message;
}
