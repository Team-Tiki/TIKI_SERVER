package com.tiki.server.common.dto;

import jakarta.validation.constraints.NotNull;

public interface BaseResponse {
	boolean success();
	@NotNull String message();
}
