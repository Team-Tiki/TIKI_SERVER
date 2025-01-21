package com.tiki.server.external.dto.request;

import jakarta.validation.constraints.NotNull;

public record S3DeleteRequest(
	@NotNull String fileKey
) {
}
