package com.tiki.server.memberteammanager.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    UPDATE_NAME("팀 내 이름 변경 성공"),
    LEAVE_TEAM("팀 탈퇴 성공"),
    KICK_TEAM("팀 퇴출 성공"),
    GET_POSITION("직책 불러오기 성공");

    private final String message;
}
