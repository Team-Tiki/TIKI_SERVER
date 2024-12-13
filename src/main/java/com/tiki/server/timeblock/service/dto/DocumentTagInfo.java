package com.tiki.server.timeblock.service.dto;

import static lombok.AccessLevel.PRIVATE;

import com.tiki.server.document.entity.Document;
import com.tiki.server.documenttimeblockmanager.entity.DTBManager;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record DocumentTagInfo(
	long documentId,
	@NonNull String fileName,
	@NonNull String fileUrl,
	long tagId
) {

	public static DocumentTagInfo of(final Document document, final DTBManager dtbManager) {
		return DocumentTagInfo.builder()
			.documentId(document.getId())
			.fileName(document.getFileName())
			.fileUrl(document.getFileUrl())
			.tagId(dtbManager.getId())
			.build();
	}
}
