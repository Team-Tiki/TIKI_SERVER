package com.tiki.server.common.dto;

import static lombok.AccessLevel.PRIVATE;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record AuthResponse(
	boolean success,
	int code,
	@NonNull String message
) implements BaseResponse {

	public static AuthResponse of(int code, String message) {
		return AuthResponse.builder()
			.success(false)
			.code(code)
			.message(message)
			.build();
	}
}
