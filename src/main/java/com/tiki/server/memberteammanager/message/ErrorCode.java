package com.tiki.server.memberteammanager.message;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	CONFLICT_TEAM_MEMBER(BAD_REQUEST, "이미 존재하는 팀원입니다"),
	CANNOT_QUIT_TEAM(BAD_REQUEST, "팀을 탈퇴할 수 없습니다."),

	/* 403 FORBIDDEN : 권한 없음 */
	INVALID_AUTHORIZATION(FORBIDDEN, "권한이 없습니다."),

	/* 404 NOT_FOUND : 자원을 찾을 수 없음 */
	INVALID_MEMBER_TEAM_MANAGER(NOT_FOUND, "팀에 존재하지 않는 회원입니다.");

	private final HttpStatus httpStatus;
	private final String message;
}
