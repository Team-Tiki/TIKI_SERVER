package com.tiki.server.note.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
public class NoteTemplate extends Note {

    private String answerWhatActivity;
    private String answerHowToPrepare;
    private String answerWhatIsDisappointedThing;
    private String answerHowToFix;

    // 정적 팩토리 메서드
    public static NoteTemplate of(
            String title,
            boolean complete,
            LocalDate startDate,
            LocalDate endDate,
            String answerWhatActivity,
            String answerHowToPrepare,
            String answerWhatIsDisappointedThing,
            String answerHowToFix,
            long teamId
    ) {
        return new NoteTemplate(
                title,
                complete,
                startDate,
                endDate,
                answerWhatActivity,
                answerHowToPrepare,
                answerWhatIsDisappointedThing,
                answerHowToFix,
                teamId
        );
    }

    // 생성자
    private NoteTemplate(
            String title,
            boolean complete,
            LocalDate startDate,
            LocalDate endDate,
            String answerWhatActivity,
            String answerHowToPrepare,
            String answerWhatIsDisappointedThing,
            String answerHowToFix,
            long teamId
    ) {
        super(title, complete, startDate, endDate, teamId);
        this.answerWhatActivity = answerWhatActivity;
        this.answerHowToPrepare = answerHowToPrepare;
        this.answerWhatIsDisappointedThing = answerWhatIsDisappointedThing;
        this.answerHowToFix = answerHowToFix;
    }

    public String getContent() {
        return this.answerWhatActivity + " " +
                this.answerHowToPrepare + " " +
                this.answerWhatIsDisappointedThing + " " +
                this.answerHowToFix;
    }
}
