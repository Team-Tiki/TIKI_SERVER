package com.tiki.server.note.entity;

import com.tiki.server.team.entity.Team;
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
public class NoteTemplate implements Note {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    private NoteTemplate(
            String title,
            boolean complete,
            LocalDate startDate,
            LocalDate endDate,
            String answerWhatActivity,
            String answerHowToPrepare,
            String answerWhatIsDisappointedThing,
            String answerHowToFix,
            Team team
    ) {
        this.title = title;
        this.complete = complete;
        this.startDate = startDate;
        this.endDate = endDate;
        this.answerWhatActivity = answerWhatActivity;
        this.answerHowToPrepare = answerHowToPrepare;
        this.answerWhatIsDisappointedThing = answerWhatIsDisappointedThing;
        this.answerHowToFix = answerHowToFix;
        this.team = team;
    }

    public static NoteTemplate of(
            String title,
            boolean complete,
            LocalDate startDate,
            LocalDate endDate,
            String answerWhatActivity,
            String answerHowToPrepare,
            String answerWhatIsDisappointedThing,
            String answerHowToFix,
            Team team
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
                .team(team)
                .build();
    }

    @Override
    public String getContent() {
        return this.answerWhatActivity + " " +
                this.answerHowToPrepare + " " +
                this.answerWhatIsDisappointedThing + " " +
                this.answerHowToFix;
    }
}
