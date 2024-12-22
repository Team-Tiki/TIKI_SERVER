package com.tiki.server.email.teaminvitation.messages;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    CREATE_INVITATION("초대에 성공했습니다");

    private final String message;
}
