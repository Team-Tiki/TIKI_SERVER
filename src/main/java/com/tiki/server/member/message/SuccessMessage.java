package com.tiki.server.member.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

	TEMP("컴파일 에러 방지용");

	private final String message;
}
