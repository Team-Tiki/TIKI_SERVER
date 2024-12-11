package com.tiki.server.timeblock.dto.response;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.tiki.server.document.entity.Document;

import com.tiki.server.note.entity.Note;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record TimeBlockDetailGetResponse(
	List<DocumentGetResponse> documents,
	List<NoteNameGetResponse> notes
) {

	public static TimeBlockDetailGetResponse from(List<Document> documents, List<Note> notes) {
		return TimeBlockDetailGetResponse.builder()
			.documents(documents.stream().map(DocumentGetResponse::from).toList())
			.notes(notes.stream().map(NoteNameGetResponse::from).toList())
			.build();
	}

	@Builder(access = PRIVATE)
	public record DocumentGetResponse(
		long documentId,
		@NonNull String fileName,
		@NonNull String fileUrl
	) {

		public static DocumentGetResponse from(Document document) {
			return DocumentGetResponse.builder()
				.documentId(document.getId())
				.fileName(document.getFileName())
				.fileUrl(document.getFileUrl())
				.build();
		}
	}

	@Builder(access = PRIVATE)
	public record NoteNameGetResponse(
		long noteId,
		@NonNull String noteName
	) {

		public static NoteNameGetResponse from(final Note note) {
			return NoteNameGetResponse.builder()
				.noteId(note.getId())
				.noteName(note.getTitle())
				.build();
		}
	}
}
