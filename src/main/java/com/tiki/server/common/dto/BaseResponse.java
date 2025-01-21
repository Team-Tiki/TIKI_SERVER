package com.tiki.server.common.dto;

import jakarta.validation.constraints.NotNull;

public interface BaseResponse {
	@NotNull boolean success();
	@NotNull String message();
}
