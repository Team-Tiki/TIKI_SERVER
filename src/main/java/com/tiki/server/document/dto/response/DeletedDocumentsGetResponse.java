package com.tiki.server.document.dto.response;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.tiki.server.document.entity.DeletedDocument;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record DeletedDocumentsGetResponse(
	List<DeletedDocumentGetResponse> deletedDocuments
) {

	public static DeletedDocumentsGetResponse from(final List<DeletedDocument> deletedDocuments) {
		return DeletedDocumentsGetResponse.builder()
				.deletedDocuments(deletedDocuments.stream().map(DeletedDocumentGetResponse::from).toList())
				.build();
	}

	@Builder(access = PRIVATE)
	private record DeletedDocumentGetResponse(
		long documentId,
		@NonNull String name,
		@NonNull String url,
		double capacity
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
