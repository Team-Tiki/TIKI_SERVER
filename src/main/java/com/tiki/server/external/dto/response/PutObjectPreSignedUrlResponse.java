package com.tiki.server.external.dto.response;

import static lombok.AccessLevel.PRIVATE;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(access = PRIVATE)
public record PutObjectPreSignedUrlResponse(
	@NotNull String fileName,
	@NotNull String url
) {

	public static PutObjectPreSignedUrlResponse of(final String fileName, final String url) {
		return PutObjectPreSignedUrlResponse.builder()
			.fileName(fileName)
			.url(url)
			.build();
	}
}
