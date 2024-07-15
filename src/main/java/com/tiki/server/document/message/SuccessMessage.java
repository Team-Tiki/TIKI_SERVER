package com.tiki.server.document.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

	SUCCESS_GET_DOCUMENTS("전체 문서 조회 성공");

	private final String message;
}
