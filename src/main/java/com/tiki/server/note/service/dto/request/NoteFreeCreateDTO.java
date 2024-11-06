package com.tiki.server.note.service.dto.request;

import com.tiki.server.note.controller.dto.request.NoteFreeCreateRequest;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;

public record NoteFreeCreateDTO(
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
    public static NoteFreeCreateDTO of(
            final NoteFreeCreateRequest request,
            final long memberId
    ) {
        return new NoteFreeCreateDTO(
                request.title(),
                request.complete(),
                request.startDate(),
                request.endDate(),
                request.contents(),
                request.teamId(),
                request.timeBlockIds(),
                request.documentIds(),
                memberId
        );
    }
}
