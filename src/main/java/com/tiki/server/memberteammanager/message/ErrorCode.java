package com.tiki.server.memberteammanager.message;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	/* 404 NOT_FOUND : 자원을 찾을 수 없음 */
	INVALID_MEMBER_TEAM_MANAGER(NOT_FOUND, "팀에 존재하지 않는 회원입니다.");

	private final HttpStatus httpStatus;
	private final String message;
}
