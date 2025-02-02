package com.tiki.server.note.controller.dto.request;

import com.tiki.server.common.util.Validator;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;

public record NoteTemplateUpdateRequest(
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
            public NoteTemplateUpdateRequest(final String title, final boolean complete, final LocalDate startDate, final LocalDate endDate, final String answerWhatActivity, final String answerHowToPrepare, final String answerWhatIsDisappointedThing, final String answerHowToFix, final List<Long> timeBlockIds, final List<Long> documentIds, final long teamId) {
                Validator.validateLength(title, 30);
                this.title = title;
                this.complete = complete;
                this.startDate = startDate;
                this.endDate = endDate;
                this.answerWhatActivity = answerWhatActivity;
                this.answerHowToPrepare = answerHowToPrepare;
                this.answerWhatIsDisappointedThing = answerWhatIsDisappointedThing;
                this.answerHowToFix = answerHowToFix;
                this.timeBlockIds = timeBlockIds;
                this.documentIds = documentIds;
                this.teamId = teamId;
            }
}