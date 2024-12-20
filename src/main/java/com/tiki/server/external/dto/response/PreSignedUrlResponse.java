package com.tiki.server.external.dto.response;

import static lombok.AccessLevel.PRIVATE;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(access = PRIVATE)
public record PreSignedUrlResponse(
	@NotNull String fileName,
	@NotNull String url
) {

	public static PreSignedUrlResponse of(String fileName, String url) {
		return PreSignedUrlResponse.builder()
			.fileName(fileName)
			.url(url)
			.build();
	}
}
