package com.tiki.server.note.service.dto.request;

import com.tiki.server.note.controller.dto.request.NoteTemplateCreateRequest;
import lombok.NonNull;

import java.time.LocalDate;

public record NoteTemplateCreateDTO(

        @NonNull String title,
        boolean complete,
        @NonNull LocalDate startDate,
        @NonNull LocalDate endDate,
        @NonNull String answerWhatActivity,
        @NonNull String answerHowToPrepare,
        @NonNull String answerWhatIsDisappointedThing,
        @NonNull String answerHowToFix,
        long teamId,
        long memberId
) {

    public static NoteTemplateCreateDTO of(
            final NoteTemplateCreateRequest request,
            final long memberId
    ) {
        return new NoteTemplateCreateDTO(
                request.title(),
                request.complete(),
                request.startDate(),
                request.endDate(),
                request.answerWhatActivity(),
                request.answerHowToPrepare(),
                request.answerWhatIsDisappointedThing(),
                request.answerHowToFix(),
                request.teamId(),
                memberId
        );
    }
}
