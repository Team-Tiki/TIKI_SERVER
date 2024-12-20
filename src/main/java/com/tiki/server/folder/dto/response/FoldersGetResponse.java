package com.tiki.server.folder.dto.response;

import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDateTime;
import java.util.List;

import com.tiki.server.folder.entity.Folder;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(access = PRIVATE)
public record FoldersGetResponse(
	@NotNull List<FolderInfoGetResponse> folders
) {

	public static FoldersGetResponse from(List<Folder> folders) {
		return FoldersGetResponse.builder()
				.folders(folders.stream().map(FolderInfoGetResponse::from).toList())
				.build();
	}

	@Builder(access = PRIVATE)
	private record FolderInfoGetResponse(
		@NotNull long id,
		@NotNull String name,
		@NotNull LocalDateTime createdTime,
		@NotNull String path
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
