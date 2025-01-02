package com.tiki.server.note.service.dto.response;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public record NoteListGetServiceResponse(
        @NotNull List<NoteGetResponse> noteGetResponseList
) {

    public static NoteListGetServiceResponse of(final List<NoteGetResponse> noteGetResponseList) {
        return new NoteListGetServiceResponse(noteGetResponseList);
    }
}