package com.tiki.server.common.dto;

import static lombok.AccessLevel.PRIVATE;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(access = PRIVATE)
public record ErrorCodeResponse(
	@NotNull boolean success,
	@NotNull int code,
	@NotNull String message
) implements BaseResponse {

	public static ErrorCodeResponse of(final int code, final String message) {
		return ErrorCodeResponse.builder()
			.success(false)
			.code(code)
			.message(message)
			.build();
	}
}
