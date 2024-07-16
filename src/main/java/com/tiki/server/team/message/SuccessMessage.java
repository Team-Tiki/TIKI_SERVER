package com.tiki.server.team.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

	SUCCESS_CREATE_TEAM("팀 생성 성공"),
	SUCCESS_GET_TEAMS("전체 팀 불러오기 성공"),
	SUCCESS_GET_CATEGORIES("카테고리 리스트 불러오기 성공"),
	SUCCESS_GET_JOINED_TEAM("소속 팀 불러오기 성공");

	private final String message;
}
