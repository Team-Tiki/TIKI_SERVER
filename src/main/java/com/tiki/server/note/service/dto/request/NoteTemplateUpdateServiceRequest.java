package com.tiki.server.note.service.dto.request;

import com.tiki.server.note.controller.dto.request.NoteTemplateUpdateRequest;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record NoteTemplateUpdateServiceRequest(
    @NotNull long noteId,
    @NotNull String title,
    @NotNull boolean complete,
    @NotNull  LocalDate startDate,
    @NotNull  LocalDate endDate,
    @NotNull  String answerWhatActivity,
    @NotNull  String answerHowToPrepare,
    @NotNull  String answerWhatIsDisappointedThing,
    @NotNull String answerHowToFix,
    @NotNull long teamId,
    @NotNull List<Long> timeBlockIds,
    @NotNull List<Long> documentIds,
    @NotNull long memberId
) {

    public static NoteTemplateUpdateServiceRequest of(
            final NoteTemplateUpdateRequest request,
            final long noteId,
            final long memberId
    ) {
        return new NoteTemplateUpdateServiceRequest(
                noteId,
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