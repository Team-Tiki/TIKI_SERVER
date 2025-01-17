package com.tiki.server.timeblock.service.dto;

import static lombok.AccessLevel.PRIVATE;

import com.tiki.server.document.entity.Document;
import com.tiki.server.documenttimeblockmanager.entity.DTBManager;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(access = PRIVATE)
public record DocumentTagInfo(
	@NotNull long documentId,
	@NotNull String fileName,
	@NotNull String fileUrl,
	@NotNull long capacity,
	@NotNull long tagId
) {

	public static DocumentTagInfo of(final Document document, final DTBManager dtbManager) {
		return DocumentTagInfo.builder()
			.documentId(document.getId())
			.fileName(document.getFileName())
			.fileUrl(document.getFileUrl())
			.capacity(document.getCapacity())
			.tagId(dtbManager.getId())
			.build();
	}
}
