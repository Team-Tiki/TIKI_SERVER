package com.tiki.server.document.dto.response;

import static lombok.AccessLevel.PRIVATE;

import lombok.Builder;

@Builder(access = PRIVATE)
public record DocumentCreateResponse(
	long documentId
) {

	public static DocumentCreateResponse from(long documentId) {
		return DocumentCreateResponse.builder()
			.documentId(documentId)
			.build();
	}
}
