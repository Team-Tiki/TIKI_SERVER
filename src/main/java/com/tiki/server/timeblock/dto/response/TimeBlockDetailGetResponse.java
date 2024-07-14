package com.tiki.server.timeblock.dto.response;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.tiki.server.document.vo.DocumentVO;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record TimeBlockDetailGetResponse(
	List<DocumentGetResponse> documents
) {

	public static TimeBlockDetailGetResponse from(List<DocumentVO> documents) {
		return TimeBlockDetailGetResponse.builder()
			.documents(documents.stream().map(DocumentGetResponse::from).toList())
			.build();
	}

	@Builder(access = PRIVATE)
	public record DocumentGetResponse(
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
