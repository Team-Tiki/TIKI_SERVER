package com.tiki.server.note.service.dto.request;

import com.tiki.server.note.entity.vo.*;
import lombok.NonNull;

import java.time.LocalDate;

public record NoteTemplateCreateDTO(

        @NonNull TitleVo titleVo,
        boolean complete,
        @NonNull LocalDate startDate,
        @NonNull LocalDate endDate,
        @NonNull AnswerWhatActivityVO answerWhatActivity,
        @NonNull AnswerHowToPrepareVO answerHowToPrepare,
        @NonNull AnswerWhatIsDisappointedThingVO answerWhatIsDisappointedThing,
        @NonNull AnswerHowToFixVO answerHowToFix,
        long teamId,
        long memberId
) {

    public static NoteTemplateCreateDTO of(
            final TitleVo title,
            final boolean complete,
            final LocalDate startDate,
            final LocalDate endDate,
            final AnswerWhatActivityVO answerWhatActivity,
            final AnswerHowToPrepareVO answerHowToPrepare,
            final AnswerWhatIsDisappointedThingVO answerWhatIsDisappointedThing,
            final AnswerHowToFixVO answerHowToFix,
            final long teamId,
            final long memberId
    ) {
        return new NoteTemplateCreateDTO(
                title,
                complete,
                startDate,
                endDate,
                answerWhatActivity,
                answerHowToPrepare,
                answerWhatIsDisappointedThing,
                answerHowToFix,
                teamId,
                memberId
        );
    }
}
