package com.tiki.server.document.dto.response;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(access = PRIVATE)
public record DocumentsCreateResponse(
	@NotNull List<DocumentCreateResponse> response
) {

	public static DocumentsCreateResponse from(final List<Long> documentIds) {
		return DocumentsCreateResponse.builder()
			.response(documentIds.stream().map(DocumentCreateResponse::from).toList())
			.build();
	}

	@Builder(access = PRIVATE)
	private record DocumentCreateResponse(
		@NotNull long documentId
	) {

		public static DocumentCreateResponse from(final long documentId) {
			return DocumentCreateResponse.builder()
				.documentId(documentId)
				.build();
		}
	}
}
