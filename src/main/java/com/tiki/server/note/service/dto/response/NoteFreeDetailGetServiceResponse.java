package com.tiki.server.note.service.dto.response;

import com.tiki.server.common.util.ContentDecoder;
import com.tiki.server.document.entity.Document;
import com.tiki.server.document.service.dto.response.DocumentTagGetServiceResponse;
import com.tiki.server.note.entity.Note;
import com.tiki.server.note.entity.NoteType;
import com.tiki.server.timeblock.entity.TimeBlock;
import com.tiki.server.timeblock.service.dto.response.TimeBlockTagServiceResponse;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record NoteFreeDetailGetServiceResponse(
	@NotNull long noteId,
	@NotNull NoteType noteType,
	@NotNull String title,
	@NotNull String author,
	@NotNull LocalDate startDate,
	@NotNull LocalDate endDate,
	@NotNull boolean complete,
	@NotNull String contents,
	@NotNull List<DocumentTagGetServiceResponse> documentList,
	@NotNull List<TimeBlockTagServiceResponse> timeBlockList
) implements NoteDetailGetServiceResponse {

	public static NoteFreeDetailGetServiceResponse of(
		final Note note,
		final String author,
		final List<Document> documentList,
		final List<TimeBlock> timeBlockList
	) {
		return new NoteFreeDetailGetServiceResponse(
			note.getId(),
			NoteType.FREE,
			note.getTitle(),
			author,
			note.getStartDate(),
			note.getEndDate(),
			note.isComplete(),
			ContentDecoder.decodeNoteFree(note.getContents()),
			documentList.stream().map(DocumentTagGetServiceResponse::from).toList(),
			timeBlockList.stream().map(TimeBlockTagServiceResponse::from).toList()
		);
	}
}
