package com.tiki.server.team.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

	SUCCESS_CREATE_TEAM("팀 생성 성공"),
	SUCCESS_UPDATE_TEAM_NAME("팀 이름 변경 성공"),
	SUCCESS_UPDATE_TEAM_ICON("팀 아이콘 변경 성공"),
	SUCCESS_ALTER_AUTHORITY("어드민 권한 위임 성공"),
	SUCCESS_GET_TEAMS("전체 팀 불러오기 성공"),
	SUCCESS_GET_CATEGORIES("카테고리 리스트 불러오기 성공"),
	SUCCESS_GET_TEAM_INFORM("팀 설정 정보 불러오기 성공"),
	SUCCESS_GET_JOINED_TEAM("소속 팀 불러오기 성공"),
	SUCCESS_GET_CAPACITY_INFO("팀 용량 정보 조회 성공");

	private final String message;
}
