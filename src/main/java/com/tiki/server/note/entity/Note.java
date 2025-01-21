package com.tiki.server.note.entity;

import com.tiki.server.common.entity.BaseTime;
import com.tiki.server.note.exception.NoteException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.tiki.server.note.message.ErrorCode.UPDATE_ONLY_AUTHOR;
import static com.tiki.server.note.message.ErrorCode.UPDATE_ONLY_BELONGING_TEAM;
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

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private boolean complete;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    private Long memberId;

    @Column(nullable = false)
    private long teamId;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NoteType noteType;

    public static Note of(
            final String title,
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
                .complete(complete)
                .startDate(startDate)
                .endDate(endDate)
                .memberId(memberId)
                .teamId(teamId)
                .contents(contents)
                .noteType(noteType)
                .build();
    }

    public void updateValue(
            final long clientId,
            final long clientTeamId,
            final String title,
            final String contents,
            final LocalDate startDate,
            final LocalDate endDate,
            final boolean complete,
            final NoteType noteType
    ) {
        checkAuthor(clientId);
        checkTeam(clientTeamId);
        this.title = title;
        this.contents = contents;
        this.startDate = startDate;
        this.endDate = endDate;
        this.complete = complete;
        this.noteType = noteType;
    }

    public void deleteMemberDependency() {
        this.memberId = null;
    }

    private void checkAuthor(final long clientId) {
        if (this.memberId != clientId) {
            throw new NoteException(UPDATE_ONLY_AUTHOR);
        }
    }

    private void checkTeam(final long clientTeamId) {
        if (this.teamId != clientTeamId) {
            throw new NoteException(UPDATE_ONLY_BELONGING_TEAM);
        }
    }
}