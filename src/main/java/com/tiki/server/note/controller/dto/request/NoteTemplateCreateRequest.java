package com.tiki.server.note.controller.dto.request;

import jakarta.validation.constraints.Size;
import lombok.NonNull;

import java.time.LocalDate;

public record NoteTemplateCreateRequest(
        @NonNull
        String title,
        boolean complete,
        @NonNull LocalDate startDate,
        @NonNull LocalDate endDate,
        @NonNull String answerWhatActivity,
        @NonNull String answerHowToPrepare,
        @NonNull String answerWhatIsDisappointedThing,
        @NonNull String answerHowToFix,
        long teamId
) {
}