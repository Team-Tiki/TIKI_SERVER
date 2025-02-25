package com.tiki.server.external.dto.response;

import jakarta.validation.constraints.NotNull;

public record GetObjectPreSignedUrlResponse(
	@NotNull String url
) {

	public static GetObjectPreSignedUrlResponse from(final String url) {
		return new GetObjectPreSignedUrlResponse(url);
	}
}
