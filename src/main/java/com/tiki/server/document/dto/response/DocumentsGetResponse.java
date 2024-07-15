package com.tiki.server.document.dto.response;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.tiki.server.document.vo.DocumentVO;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record DocumentsGetResponse(
	List<DocumentGetResponse> documents
) {

	public static DocumentsGetResponse from(List<DocumentVO> documents) {
		return DocumentsGetResponse.builder()
			.documents(documents.stream().map(DocumentGetResponse::from).toList())
			.build();
	}

	@Builder(access = PRIVATE)
	private record DocumentGetResponse(
		long documentId,
		@NonNull String fileName,
		@NonNull String fileUrl
	) {

		public static DocumentGetResponse from(DocumentVO document) {
			return DocumentGetResponse.builder()
				.documentId(document.documentId())
				.fileName(document.fileName())
				.fileUrl(document.fileUrl())
				.build();
		}
	}
}
