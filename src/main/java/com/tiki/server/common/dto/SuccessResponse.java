package com.tiki.server.common.dto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static lombok.AccessLevel.PRIVATE;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(access = PRIVATE)
public record SuccessResponse<T>(
	@NotNull boolean success,
	@NotNull String message,
	@JsonInclude(value = NON_NULL) T data
) implements BaseResponse {

	public static <T> SuccessResponse<T> success(final String message, final T data) {
		return SuccessResponse.<T>builder().success(true).message(message).data(data).build();
	}

	public static SuccessResponse<?> success(final String message) {
		return SuccessResponse.builder().success(true).message(message).build();
	}
}
