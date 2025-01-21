package com.tiki.server.folder.dto.response;

import static lombok.AccessLevel.PRIVATE;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(access = PRIVATE)
public record FolderCreateResponse(
	@NotNull long folderId
) {

	public static FolderCreateResponse from(final long folderId) {
		return FolderCreateResponse.builder()
			.folderId(folderId)
			.build();
	}
}
