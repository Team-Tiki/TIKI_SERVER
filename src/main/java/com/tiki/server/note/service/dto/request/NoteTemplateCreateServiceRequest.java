package com.tiki.server.note.service.dto.request;

import com.tiki.server.note.controller.dto.request.NoteTemplateCreateRequest;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record NoteTemplateCreateServiceRequest(
    @NotNull String title,
    @NotNull boolean complete,
    @NotNull LocalDate startDate,
    @NotNull LocalDate endDate,
    @NotNull String answerWhatActivity,
    @NotNull String answerHowToPrepare,
    @NotNull String answerWhatIsDisappointedThing,
    @NotNull String answerHowToFix,
    @NotNull long teamId,
    @NotNull List<Long> timeBlockIds,
    @NotNull List<Long> documentIds,
    @NotNull long memberId
) {
    public static NoteTemplateCreateServiceRequest of(
            final NoteTemplateCreateRequest request,
            final long memberId
    ) {
        return new NoteTemplateCreateServiceRequest(
                request.title(),
                request.complete(),
                request.startDate(),
                request.endDate(),
                request.answerWhatActivity(),
                request.answerHowToPrepare(),
                request.answerWhatIsDisappointedThing(),
                request.answerHowToFix(),
                request.teamId(),
                request.timeBlockIds(),
                request.documentIds(),
                memberId
        );
    }
}
