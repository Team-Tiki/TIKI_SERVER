package com.tiki.server.auth.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

	SUCCESS_SIGN_UP("회원가입 성공");

	private final String message;
}
