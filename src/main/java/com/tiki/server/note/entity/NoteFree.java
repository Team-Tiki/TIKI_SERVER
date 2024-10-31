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
public class NoteFree implements Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    private Long id;

    private String title;

    private boolean complete;

    private LocalDate startDate;

    private LocalDate endDate;

    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    private NoteFree(
            String title,
            boolean complete,
            LocalDate startDate,
            LocalDate endDate,
            String contents,
            Team team) {
        this.title = title;
        this.complete = complete;
        this.startDate = startDate;
        this.endDate = endDate;
        this.contents = contents;
        this.team = team;
    }

    public static NoteFree of(
            String title,
            boolean complete,
            LocalDate startDate,
            LocalDate endDate,
            String contents,
            Team team) {
        return NoteFree.builder()
                .title(title)
                .complete(complete)
                .startDate(startDate)
                .endDate(endDate)
                .contents(contents)
                .team(team)
                .build();
    }

    @Override
    public String getContent() {
        return this.contents;
    }
}
