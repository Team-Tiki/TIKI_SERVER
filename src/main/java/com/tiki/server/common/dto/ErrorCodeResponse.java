package com.tiki.server.common.dto;

import static lombok.AccessLevel.PRIVATE;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record ErrorCodeResponse(
	boolean success,
	int code,
	@NonNull String message
) implements BaseResponse {

	public static ErrorCodeResponse of(int code, String message) {
		return ErrorCodeResponse.builder()
			.success(false)
			.code(code)
			.message(message)
			.build();
	}
}
