package com.tiki.server.note.controller.dto.request;

import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;

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
        @NonNull List<Long> timeBlockIds,
        @NonNull List<Long> documentIds,
        long teamId
) {
}