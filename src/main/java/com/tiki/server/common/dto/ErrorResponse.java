package com.tiki.server.common.dto;

import static lombok.AccessLevel.PRIVATE;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(access = PRIVATE)
public record ErrorResponse(
	@NotNull boolean success,
	@NotNull String message
) implements BaseResponse {

	public static ErrorResponse of(final String message) {
		return ErrorResponse.builder()
			.success(false)
			.message(message)
			.build();
	}
}
