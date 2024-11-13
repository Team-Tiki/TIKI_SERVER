package com.tiki.server.folder.dto.response;

import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDateTime;

import com.tiki.server.folder.entity.Folder;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record FoldersGetResponse(
) {

	@Builder(access = PRIVATE)
	private record FolderGetResponse(
		long id,
		@NonNull String name,
		@NonNull LocalDateTime createdTime,
		@NonNull String path
	) {

		private static FolderGetResponse from(Folder folder) {
			return FolderGetResponse.builder()
					.id(folder.getId())
					.name(folder.getName())
					.createdTime(folder.getCreatedAt())
					.path(folder.getPath())
					.build();
		}
	}
}
