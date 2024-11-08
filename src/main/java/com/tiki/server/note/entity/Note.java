package com.tiki.server.note.entity;

import com.tiki.server.common.entity.BaseTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
public class Note extends BaseTime {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "note_id")
    private Long id;

    private String title;
    private String author;

    private boolean complete;

    private LocalDate startDate;

    private LocalDate endDate;

    private long memberId;

    private long teamId;

    private String contents;

    private NoteType noteType;

    public static Note of(
            final String title,
            final String author,
            final boolean complete,
            final LocalDate startDate,
            final LocalDate endDate,
            final String contents,
            final long memberId,
            final long teamId,
            final NoteType noteType
    ) {
        return Note.builder()
                .title(title)
                .author(author)
                .complete(complete)
                .startDate(startDate)
                .endDate(endDate)
                .memberId(memberId)
                .teamId(teamId)
                .contents(contents)
                .noteType(noteType)
                .build();
    }
}