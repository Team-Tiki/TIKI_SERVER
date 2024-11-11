package com.tiki.server.note.service.dto.request;

import com.tiki.server.note.controller.dto.request.NoteFreeUpdateRequest;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;

public record NoteFreeUpdateServiceRequest(
        long noteId,
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

    public static NoteFreeUpdateServiceRequest of(
            final NoteFreeUpdateRequest request,
            final long noteId,
            final long memberId
    ) {
        return new NoteFreeUpdateServiceRequest(
                noteId,
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