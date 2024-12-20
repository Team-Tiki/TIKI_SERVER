package com.tiki.server.note.service.dto.response;

import com.tiki.server.note.entity.Note;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public record NoteGetResponse(
    @NotNull long noteId,
    @NotNull String title,
    @NotNull LocalDate startDate,
    @NotNull LocalDate endDate,
    @NotNull String author,
    @NotNull boolean complete,
    @NotNull LocalDateTime lastUpdatedAt
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
