package com.tiki.server.folder.dto.response;

import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDateTime;
import java.util.List;

import com.tiki.server.folder.entity.Folder;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record FoldersGetResponse(
	List<FolderInfoGetResponse> folders
) {

	public static FoldersGetResponse from(List<Folder> folders) {
		return FoldersGetResponse.builder()
				.folders(folders.stream().map(FolderInfoGetResponse::from).toList())
				.build();
	}

	@Builder(access = PRIVATE)
	private record FolderInfoGetResponse(
		long id,
		@NonNull String name,
		@NonNull LocalDateTime createdTime,
		@NonNull String path
	) {

		private static FolderInfoGetResponse from(Folder folder) {
			return FolderInfoGetResponse.builder()
					.id(folder.getId())
					.name(folder.getName())
					.createdTime(folder.getCreatedAt())
					.path(folder.getPath())
					.build();
		}
	}
}
