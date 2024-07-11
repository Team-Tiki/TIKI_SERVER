package com.tiki.server.external.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

	PRESIGNED_URL_GET_SUCCESS("S3 PRESIGNED URL 불러오기 성공");

	private final String message;
}
