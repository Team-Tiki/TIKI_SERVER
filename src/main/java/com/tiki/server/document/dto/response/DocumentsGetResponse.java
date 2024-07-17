package com.tiki.server.document.dto.response;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.tiki.server.document.entity.Document;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record DocumentsGetResponse(
	List<DocumentGetResponse> documents
) {

	public static DocumentsGetResponse from(List<Document> documents) {
		return DocumentsGetResponse.builder()
			.documents(documents.stream().map(DocumentGetResponse::from).toList())
			.build();
	}

	@Builder(access = PRIVATE)
	private record DocumentGetResponse(
		long documentId,
		@NonNull String fileName,
		@NonNull String fileUrl,
		@NonNull String blockName,
		@NonNull String color
	) {

		public static DocumentGetResponse from(Document document) {
			return DocumentGetResponse.builder()
				.documentId(document.getId())
				.fileName(document.getFileName())
				.fileUrl(document.getFileUrl())
				.blockName(document.getTimeBlock().getName())
				.color(document.getTimeBlock().getColor())
				.build();
		}
	}
}
