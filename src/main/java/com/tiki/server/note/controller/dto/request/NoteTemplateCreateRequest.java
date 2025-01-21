package com.tiki.server.note.controller.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;

public record NoteTemplateCreateRequest(
        @NotNull String title,
        @NotNull boolean complete,
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate,
        @NotNull String answerWhatActivity,
        @NotNull String answerHowToPrepare,
        @NotNull String answerWhatIsDisappointedThing,
        @NotNull String answerHowToFix,
        @NotNull List<Long> timeBlockIds,
        @NotNull List<Long> documentIds,
        @NotNull long teamId
) {
}