package com.tiki.server.folder.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

	SUCCESS_GET_FOLDERS("폴더 목록 조회 성공"),
	SUCCESS_CREATE_FOLDER("폴더 생성 성공");

	private final String message;
}
