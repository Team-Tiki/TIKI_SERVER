package com.tiki.server.note.controller.dto.request;

import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;

public record NoteFreeUpdateRequest(
        @NonNull
        String title,
        boolean complete,
        @NonNull LocalDate startDate,
        @NonNull LocalDate endDate,
        @NonNull String contents,
        List<Long> timeBlockIds,
        List<Long> documentIds,
        long teamId
) {
}