package com.tiki.server.folder.message;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	/* 404 NOT_FOUND : 자원을 찾을 수 없음 */
	INVALID_FOLDER(NOT_FOUND, "유효하지 않은 폴더입니다."),

	/* 409 CONFLICT : 중복된 자원 */
	FOLDER_NAME_DUPLICATE(CONFLICT, "중복된 폴더 이름입니다.");;

	private final HttpStatus httpStatus;
	private final String message;
}
