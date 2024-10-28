package com.tiki.server.folder.dto.response;

import static lombok.AccessLevel.PRIVATE;

import lombok.Builder;

@Builder(access = PRIVATE)
public record FolderCreateResponse(
	long folderId
) {

	public static FolderCreateResponse from(long folderId) {
		return FolderCreateResponse.builder()
			.folderId(folderId)
			.build();
	}
}
