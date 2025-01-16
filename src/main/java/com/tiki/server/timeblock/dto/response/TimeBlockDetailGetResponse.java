package com.tiki.server.timeblock.dto.response;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.tiki.server.note.entity.Note;
import com.tiki.server.timeblock.service.dto.DocumentTagInfo;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(access = PRIVATE)
public record TimeBlockDetailGetResponse(
	@NotNull List<DocumentDetailGetResponse> documents,
	@NotNull List<NoteNameGetResponse> notes
) {

	public static TimeBlockDetailGetResponse from(final List<DocumentTagInfo> documents, final List<Note> notes) {
		return TimeBlockDetailGetResponse.builder()
			.documents(documents.stream().map(DocumentDetailGetResponse::from).toList())
			.notes(notes.stream().map(NoteNameGetResponse::from).toList())
			.build();
	}

	@Builder(access = PRIVATE)
	private record DocumentDetailGetResponse(
		@NotNull long documentId,
		@NotNull String fileName,
		@NotNull String fileUrl,
		@NotNull long capacity,
		@NotNull long tagId
	) {

		private static DocumentDetailGetResponse from(final DocumentTagInfo document) {
			return DocumentDetailGetResponse.builder()
				.documentId(document.documentId())
				.fileName(document.fileName())
				.fileUrl(document.fileUrl())
				.capacity(document.capacity())
				.tagId(document.tagId())
				.build();
		}
	}

	@Builder(access = PRIVATE)
	private record NoteNameGetResponse(
		@NotNull long noteId,
		@NotNull String noteName
	) {

		private static NoteNameGetResponse from(final Note note) {
			return NoteNameGetResponse.builder()
				.noteId(note.getId())
				.noteName(note.getTitle())
				.build();
		}
	}
}
