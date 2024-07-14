package com.tiki.server.team.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

	SUCCESS_CREATE_TEAM("팀 생성 성공");

	private final String message;
}
