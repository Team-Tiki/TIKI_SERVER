package com.tiki.server.member.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    SUCCESS_CREATE_MEMBER("회원가입 성공");

    private final String message;
}
