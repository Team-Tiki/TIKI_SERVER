package com.tiki.server.note.service.dto.request;

import com.tiki.server.note.controller.dto.request.NoteCreateRequest;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;

public record NoteCreateDTO(
        @NonNull String title,
        boolean complete,
        @NonNull LocalDate startDate,
        @NonNull LocalDate endDate,
        @NonNull String contents,
        long teamId,
        List<Long> timeBlockIds,
        List<Long> documentIds,
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
                request.timBlockIds(),
                request.documentIds(),
                memberId
        );
    }
}
