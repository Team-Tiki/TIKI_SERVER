package com.tiki.server.external.dto.response;

import static lombok.AccessLevel.PRIVATE;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record PreSignedUrlResponse(
	@NonNull String fileName,
	@NonNull String url
) {

	public static PreSignedUrlResponse of(String fileName, String url) {
		return PreSignedUrlResponse.builder()
			.fileName(fileName)
			.url(url)
			.build();
	}
}
