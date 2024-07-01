package com.tiki.server.common.dto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static lombok.AccessLevel.PRIVATE;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record SuccessResponse<T>(
	boolean success,
	@NonNull String message,
	@JsonInclude(value = NON_NULL) T data
) implements BaseResponse {

	public static <T> SuccessResponse<T> success(String message, T data) {
		return SuccessResponse.<T>builder().success(true).message(message).data(data).build();
	}

	public static SuccessResponse<?> success(String message) {
		return SuccessResponse.builder().success(true).message(message).build();
	}
}
