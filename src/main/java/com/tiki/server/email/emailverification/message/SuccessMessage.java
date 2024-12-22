package com.tiki.server.email.emailverification.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    SUCCESS_VALIDATION("인증 성공");

    private final String message;
}
