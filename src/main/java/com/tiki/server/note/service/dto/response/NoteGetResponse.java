package com.tiki.server.note.service.dto.response;

import com.tiki.server.note.entity.Note;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record NoteGetResponse(
        long noteId,
        String title,
        LocalDate startDate,
        LocalDate endDate,
        String author,
        boolean complete,
        LocalDateTime lastUpdatedAt
) {

    public static NoteGetResponse of(final Note note, final String author) {
        return new NoteGetResponse(
                note.getId(),
                note.getTitle(),
                note.getStartDate(),
                note.getEndDate(),
                author,
                note.isComplete(),
                note.getUpdatedAt()
        );
    }
}
