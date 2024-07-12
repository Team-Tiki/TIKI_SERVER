package com.tiki.server.mail.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

	SUCCESS_SEND_EMAIL("이메일 전송 성공");

	private final String message;
}
