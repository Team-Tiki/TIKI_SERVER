package com.tiki.server.external.dto.request;

import jakarta.validation.constraints.NotNull;

public record PreSignedUrlRequest(
	@NotNull String fileFormat
) {
}
