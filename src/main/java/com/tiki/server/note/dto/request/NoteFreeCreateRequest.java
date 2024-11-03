package com.tiki.server.note.dto.request;

import jakarta.validation.constraints.Size;
import lombok.NonNull;

import java.time.LocalDate;

public record NoteFreeCreateRequest(
        @NonNull @Size(max = 100, message = "제목은 100자 이하로 입력해야 합니다.")
        String title,
        boolean complete,
        @NonNull LocalDate startDate,
        @NonNull LocalDate endDate,
        @NonNull String contents,
        long teamId
) {
}