package com.tiki.server.note.service.dto.response;

public record NoteCreateResponseDTO(
        long noteId
) {
    public static NoteCreateResponseDTO from(final long noteId) {
        return new NoteCreateResponseDTO(noteId);
    }
}
