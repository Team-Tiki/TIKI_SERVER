package com.tiki.server.drive.dto;

import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDateTime;
import java.util.List;

import com.tiki.server.document.entity.Document;
import com.tiki.server.folder.entity.Folder;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record DriveGetResponse(
	List<DocumentGetResponse> documents,
	List<FolderGetResponse> folders
) {

	public static DriveGetResponse of(final List<Document> documents, final List<Folder> folders) {
		return DriveGetResponse.builder()
			.documents(documents.stream().map(DocumentGetResponse::from).toList())
			.folders(folders.stream().map(FolderGetResponse::from).toList())
			.build();
	}

	@Builder(access = PRIVATE)
	private record DocumentGetResponse(
		long documentId,
		@NonNull String name,
		@NonNull String url,
		double capacity,
		@NonNull LocalDateTime createdTime,
		@NonNull String type
	) {

		public static DocumentGetResponse from(final Document document) {
			return DocumentGetResponse.builder()
				.documentId(document.getId())
				.name(document.getFileName())
				.url(document.getFileUrl())
				.capacity(document.getCapacity())
				.createdTime(document.getCreatedAt())
				.type("document")
				.build();
		}
	}

	@Builder(access = PRIVATE)
	private record FolderGetResponse(
		long folderId,
		@NonNull String name,
		@NonNull LocalDateTime createdTime,
		@NonNull String path,
		@NonNull String type
	) {

		private static FolderGetResponse from(Folder folder) {
			return FolderGetResponse.builder()
				.folderId(folder.getId())
				.name(folder.getName())
				.createdTime(folder.getCreatedAt())
				.path(folder.getPath())
				.type("folder")
				.build();
		}
	}
}
