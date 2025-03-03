package com.tiki.server.external.dto.response;

import static lombok.AccessLevel.PRIVATE;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(access = PRIVATE)
public record PreSignedUrlResponse(
	@NotNull String fileKey,
	@NotNull String url
) {

	public static PreSignedUrlResponse of(final String fileKey, final String url) {
		return PreSignedUrlResponse.builder()
			.fileKey(fileKey)
			.url(url)
			.build();
	}
}
