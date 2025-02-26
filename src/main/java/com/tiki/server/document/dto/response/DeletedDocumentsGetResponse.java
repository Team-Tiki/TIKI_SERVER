package com.tiki.server.document.dto.response;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(access = PRIVATE)
public record DeletedDocumentsGetResponse(
	@NotNull List<DeletedDocumentGetResponse> deletedDocuments
) {

	public static DeletedDocumentsGetResponse from(final List<DeletedDocumentResponse> deletedDocuments) {
		return DeletedDocumentsGetResponse.builder()
				.deletedDocuments(deletedDocuments.stream().map(DeletedDocumentGetResponse::from).toList())
				.build();
	}

	@Builder(access = PRIVATE)
	private record DeletedDocumentGetResponse(
		@NotNull long documentId,
		@NotNull String name,
		@NotNull String url,
		@NotNull long capacity
	) {

		private static DeletedDocumentGetResponse from(final DeletedDocumentResponse deletedDocument) {
			return DeletedDocumentGetResponse.builder()
					.documentId(deletedDocument.documentId())
					.name(deletedDocument.name())
					.url(deletedDocument.url())
					.capacity(deletedDocument.capacity())
					.build();
		}
	}
}
