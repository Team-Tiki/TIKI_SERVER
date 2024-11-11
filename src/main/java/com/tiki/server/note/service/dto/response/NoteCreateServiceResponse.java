package com.tiki.server.note.service.dto.response;

public record NoteCreateServiceResponse(
        long noteId
) {
    public static NoteCreateServiceResponse from(final long noteId) {
        return new NoteCreateServiceResponse(noteId);
    }
}