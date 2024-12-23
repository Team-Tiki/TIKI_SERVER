package com.tiki.server.note.service.dto.response;

import jakarta.validation.constraints.NotNull;

public record NoteCreateServiceResponse(
        @NotNull long noteId
) {
    public static NoteCreateServiceResponse from(final long noteId) {
        return new NoteCreateServiceResponse(noteId);
    }
}