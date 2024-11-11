package com.tiki.server.note.service.dto.request;

import com.tiki.server.note.controller.dto.request.NoteTemplateUpdateRequest;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;

public record NoteTemplateUpdateServiceRequest(
        long noteId,
        @NonNull String title,
        boolean complete,
        @NonNull LocalDate startDate,
        @NonNull LocalDate endDate,
        @NonNull String answerWhatActivity,
        @NonNull String answerHowToPrepare,
        @NonNull String answerWhatIsDisappointedThing,
        @NonNull String answerHowToFix,
        long teamId,
        List<Long> timeBlockIds,
        List<Long> documentIds,
        long memberId
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