package com.tiki.server.timeblock.service.dto;

import static lombok.AccessLevel.PRIVATE;

import com.tiki.server.document.dto.response.DocumentResponse;
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

	public static DocumentTagInfo of(final DocumentResponse document, final DTBManager dtbManager) {
		return DocumentTagInfo.builder()
			.documentId(document.documentId())
			.fileName(document.name())
			.fileUrl(document.url())
			.capacity(document.capacity())
			.tagId(dtbManager.getId())
			.build();
	}
}
