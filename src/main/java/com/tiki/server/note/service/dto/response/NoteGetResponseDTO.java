package com.tiki.server.note.service.dto.response;

import com.tiki.server.note.entity.Note;

import java.time.LocalDate;

public record NoteGetResponseDTO(
        String title,
        LocalDate startDate,
        LocalDate endDate,
        String author,
        boolean complete
) {

    public static NoteGetResponseDTO of(final Note note,final String memberName){
        return new NoteGetResponseDTO(note.getTitle(), note.getStartDate(),note.getEndDate(),memberName, note.isComplete());
    }
}
