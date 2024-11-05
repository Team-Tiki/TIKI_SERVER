package com.tiki.server.note.service.dto.request;

import com.tiki.server.note.controller.dto.request.NoteCreateRequest;
import lombok.NonNull;

import java.time.LocalDate;

public record NoteCreateDTO(
        @NonNull String title,
        boolean complete,
        @NonNull LocalDate startDate,
        @NonNull LocalDate endDate,
        @NonNull String contents,
        long teamId,
        long memberId
) {
    public static NoteCreateDTO of(
            final NoteCreateRequest request,
            final long memberId
    ) {
        return new NoteCreateDTO(
                request.title(),
                request.complete(),
                request.startDate(),
                request.endDate(),
                request.contents(),
                request.teamId(),
                memberId
        );
    }
}
