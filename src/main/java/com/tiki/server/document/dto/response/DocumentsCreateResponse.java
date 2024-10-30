package com.tiki.server.document.dto.response;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import lombok.Builder;

@Builder(access = PRIVATE)
public record DocumentsCreateResponse(
	List<DocumentCreateResponse> response
) {

	public static DocumentsCreateResponse from(List<Long> documentIds) {
		return DocumentsCreateResponse.builder()
			.response(documentIds.stream().map(DocumentCreateResponse::from).toList())
			.build();
	}

	@Builder(access = PRIVATE)
	private record DocumentCreateResponse(
		long documentId
	) {

		public static DocumentCreateResponse from(long documentId) {
			return DocumentCreateResponse.builder()
				.documentId(documentId)
				.build();
		}
	}
}
