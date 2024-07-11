package com.tiki.server.external.message;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	/* 500 INTERNAL_SERVER_ERROR : 서버 에러 */
	PRESIGNED_URL_GET_ERROR(INTERNAL_SERVER_ERROR, "S3 PRESIGNED URL 불러오기 실패"),
	FILE_DELETE_ERROR(INTERNAL_SERVER_ERROR, "S3 버킷의 파일 삭제 실패");

	private final HttpStatus httpStatus;
	private final String message;
}
