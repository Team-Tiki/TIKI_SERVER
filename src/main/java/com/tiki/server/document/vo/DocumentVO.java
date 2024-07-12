package com.tiki.server.document.vo;

import static lombok.AccessLevel.PRIVATE;

import com.tiki.server.document.entity.Document;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record DocumentVO(
	long documentId,
	@NonNull String fileName,
	@NonNull String fileUrl
) {

	public static DocumentVO from(Document document) {
		return DocumentVO.builder()
			.documentId(document.getId())
			.fileName(document.getFileName())
			.fileUrl(document.getFileUrl())
			.build();
	}
}
