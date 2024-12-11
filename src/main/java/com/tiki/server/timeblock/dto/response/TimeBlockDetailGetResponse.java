package com.tiki.server.timeblock.dto.response;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.tiki.server.note.entity.Note;
import com.tiki.server.timeblock.service.dto.DocumentTagInfo;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record TimeBlockDetailGetResponse(
	List<DocumentGetResponse> documents,
	List<NoteNameGetResponse> notes
) {

	public static TimeBlockDetailGetResponse from(List<DocumentTagInfo> documents, List<Note> notes) {
		return TimeBlockDetailGetResponse.builder()
			.documents(documents.stream().map(DocumentGetResponse::from).toList())
			.notes(notes.stream().map(NoteNameGetResponse::from).toList())
			.build();
	}

	@Builder(access = PRIVATE)
	private record DocumentGetResponse(
		long documentId,
		@NonNull String fileName,
		@NonNull String fileUrl,
		long tagId
	) {

		private static DocumentGetResponse from(DocumentTagInfo document) {
			return DocumentGetResponse.builder()
				.documentId(document.documentId())
				.fileName(document.fileName())
				.fileUrl(document.fileUrl())
				.tagId(document.tagId())
				.build();
		}
	}

	@Builder(access = PRIVATE)
	private record NoteNameGetResponse(
		long noteId,
		@NonNull String noteName
	) {

		private static NoteNameGetResponse from(final Note note) {
			return NoteNameGetResponse.builder()
				.noteId(note.getId())
				.noteName(note.getTitle())
				.build();
		}
	}
}
