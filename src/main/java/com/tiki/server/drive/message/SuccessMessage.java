package com.tiki.server.drive.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

	SUCCESS_GET_DRIVE("드라이브 조회 성공");

	private final String message;
}
