package com.tiki.server.timeblock.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

	SUCCESS_CREATE_TIME_BLOCK("타임 블록 생성 성공"),
	SUCCESS_GET_TIMELINE("타임라인 조회 성공"),
	SUCCESS_GET_ALL_TIME_BLOCK("전체 타임블록 조회 성공"),
	SUCCESS_GET_TIME_BLOCK_DETAIL("타임 블록 상세 정보 조회 성공"),
	SUCCESS_UPDATE_TIME_BLOCK("타임 블록 정보 수정 성공"),
	SUCCESS_CREATE_DOCUMENT_TAG("파일 태그 성공");

	private final String message;
}
