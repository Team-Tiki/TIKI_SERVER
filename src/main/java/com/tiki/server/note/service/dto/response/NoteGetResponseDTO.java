package com.tiki.server.note.service.dto.response;

import com.tiki.server.note.entity.Note;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record NoteGetResponseDTO(
        long noteId,
        String title,
        LocalDate startDate,
        LocalDate endDate,
        String author,
        boolean complete,
        LocalDateTime lastUpdatedAt
) {

    public static NoteGetResponseDTO of(final Note note) {
        return new NoteGetResponseDTO(
                note.getId(),
                note.getTitle(),
                note.getStartDate(),
                note.getEndDate(),
                note.getAuthor(),
                note.isComplete(),
                note.getUpdatedAt()
        );
    }
}
