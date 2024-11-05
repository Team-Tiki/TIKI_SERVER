package com.tiki.server.note.entity;

import com.tiki.server.common.entity.BaseTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@MappedSuperclass
public abstract class Note extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    private Long id;

    private String title;

    private boolean complete;

    private LocalDate startDate;

    private LocalDate endDate;

    private long teamId;

    protected Note(String title, boolean complete, LocalDate startDate, LocalDate endDate, long teamId) {
        this.title = title;
        this.complete = complete;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teamId = teamId;
    }
}