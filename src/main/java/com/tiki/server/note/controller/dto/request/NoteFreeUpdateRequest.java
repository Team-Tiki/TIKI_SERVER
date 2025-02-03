package com.tiki.server.note.controller.dto.request;

import com.tiki.server.common.util.Validator;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record NoteFreeUpdateRequest(
        @NotNull String title,
        @NotNull boolean complete,
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate,
        @NotNull String contents,
        @NotNull List<Long> timeBlockIds,
        @NotNull List<Long> documentIds,
        @NotNull long teamId
) {
            public NoteFreeUpdateRequest(final String title, final boolean complete, final LocalDate startDate, final LocalDate endDate, final String contents, final List<Long> timeBlockIds, final List<Long> documentIds, final long teamId) {
                Validator.validateLengthContainEmoji(title, 30);
                this.title = title;
                this.complete = complete;
                this.startDate = startDate;
                this.endDate = endDate;
                this.contents = contents;
                this.timeBlockIds = timeBlockIds;
                this.documentIds = documentIds;
                this.teamId = teamId;
            }
}