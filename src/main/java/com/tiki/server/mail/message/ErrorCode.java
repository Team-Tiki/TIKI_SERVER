package com.tiki.server.mail.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	/* 500 INTERNAL_SERVER_ERROR : 서버 에러 */
	WRONG_EMAIL_FORMAT(INTERNAL_SERVER_ERROR, "S3 PRESIGNED URL 불러오기 실패");

	private final HttpStatus httpStatus;
	private final String message;
}
