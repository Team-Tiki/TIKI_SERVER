package com.tiki.server.document.dto.response;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.tiki.server.document.entity.DeletedDocument;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(access = PRIVATE)
public record DeletedDocumentsGetResponse(
	@NotNull List<DeletedDocumentGetResponse> deletedDocuments
) {

	public static DeletedDocumentsGetResponse from(final List<DeletedDocument> deletedDocuments) {
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

		private static DeletedDocumentGetResponse from(final DeletedDocument deletedDocument) {
			return DeletedDocumentGetResponse.builder()
					.documentId(deletedDocument.getId())
					.name(deletedDocument.getFileName())
					.url(deletedDocument.getFileUrl())
					.capacity(deletedDocument.getCapacity())
					.build();
		}
	}
}
