package com.tiki.server.email.teaminvitation.messages;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    CREATE_TEAM_MEMBER_FROM_INVITATION("팀 가입 성공"),
    DELETE_TEAM_INVITATION_FROM_ADMIN("초대 취소 성공"),
    DELETE_TEAM_INVITATION_FROM_USER("초대 거부 성공"),
    GET_TEAM_INVITATIONS("팀 초대 목록 불러오기 성공"),
    GET_TEAM_INVITATION_INFORM("초대정보를 불러오기 성공");

    private final String message;
}
