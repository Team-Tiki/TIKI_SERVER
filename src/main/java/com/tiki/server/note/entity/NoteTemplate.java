package com.tiki.server.note.entity;

import com.tiki.server.common.entity.BaseTime;
import jakarta.persistence.*;
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
public class NoteTemplate extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    private Long id;

    private String title;

    private boolean complete;

    private LocalDate startDate;

    private LocalDate endDate;

    private String answerWhatActivity;

    private String answerHowToPrepare;

    private String answerWhatIsDisappointedThing;

    private String answerHowToFix;

    private long teamId;

    public static NoteTemplate of(
            final String title,
            final boolean complete,
            final LocalDate startDate,
            final LocalDate endDate,
            final String answerWhatActivity,
            final String answerHowToPrepare,
            final String answerWhatIsDisappointedThing,
            final String answerHowToFix,
            final long teamId
    ) {
        return NoteTemplate.builder()
                .title(title)
                .complete(complete)
                .startDate(startDate)
                .endDate(endDate)
                .answerWhatActivity(answerWhatActivity)
                .answerHowToPrepare(answerHowToPrepare)
                .answerWhatIsDisappointedThing(answerWhatIsDisappointedThing)
                .answerHowToFix(answerHowToFix)
                .teamId(teamId)
                .build();
    }

    public String getContent() {
        return this.answerWhatActivity + " " +
                this.answerHowToPrepare + " " +
                this.answerWhatIsDisappointedThing + " " +
                this.answerHowToFix;
    }
}
