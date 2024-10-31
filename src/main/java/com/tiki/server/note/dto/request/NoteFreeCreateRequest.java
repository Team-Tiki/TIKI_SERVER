package com.tiki.server.note.dto.request;

import lombok.NonNull;

import java.time.LocalDate;

public record NoteFreeCreateRequest(
        @NonNull String title,
        boolean complete,
        @NonNull LocalDate startDate,
        @NonNull LocalDate endDate,
        @NonNull String contents,
        long teamId
) {
}
