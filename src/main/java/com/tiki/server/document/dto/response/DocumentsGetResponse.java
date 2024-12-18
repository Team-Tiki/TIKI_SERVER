package com.tiki.server.document.dto.response;

import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDateTime;
import java.util.List;

import com.tiki.server.document.entity.Document;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record DocumentsGetResponse(
	List<DocumentInfoGetResponse> documents
) {

	public static DocumentsGetResponse from(final List<Document> documents) {
		return DocumentsGetResponse.builder()
				.documents(documents.stream().map(DocumentInfoGetResponse::from).toList())
				.build();
	}

	@Builder(access = PRIVATE)
	private record DocumentInfoGetResponse(
		long documentId,
		@NonNull String name,
		@NonNull String url,
		double capacity,
		@NonNull LocalDateTime createdTime
	) {

		public static DocumentInfoGetResponse from(final Document document) {
			return DocumentInfoGetResponse.builder()
					.documentId(document.getId())
					.name(document.getFileName())
					.url(document.getFileUrl())
					.capacity(document.getCapacity())
					.createdTime(document.getCreatedAt())
					.build();
		}
	}
}
