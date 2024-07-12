package com.tiki.server.timeblock.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

	SUCCESS_CREATE_TIME_BLOCK("타임 블록 생성 성공"),
	SUCCESS_GET_TIMELINE("타임라인 조회 성공");

	private final String message;
}
