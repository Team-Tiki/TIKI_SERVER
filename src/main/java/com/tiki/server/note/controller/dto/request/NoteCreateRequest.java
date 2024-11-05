package com.tiki.server.note.controller.dto.request;

import lombok.NonNull;

import java.time.LocalDate;

public record NoteCreateRequest(
        @NonNull
        String title,
        boolean complete,
        @NonNull LocalDate startDate,
        @NonNull LocalDate endDate,
        @NonNull String contents,
        long teamId
) {
}