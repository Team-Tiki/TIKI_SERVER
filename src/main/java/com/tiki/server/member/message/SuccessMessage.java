package com.tiki.server.member.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    SUCCESS_CREATE_MEMBER("회원가입 성공"),
    SUCCESS_CHANGING_PASSWORD("비밀번호 변경 성공"),
    SUCCESS_GET_MEMBER_INFORMATION("멤버 정보 불러오기 성공"),
    SUCCESS_WITHDRAWAL("회원 탈퇴 성공"),;

    private final String message;
}
