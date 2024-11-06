package com.tiki.server.notedocumentmanager.entity;

import com.tiki.server.common.entity.BaseTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
public class NoteDocumentManager extends BaseTime {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "manager_id")
    private Long id;

    @JoinColumn(name = "note_id")
    private long noteId;

    @JoinColumn(name = "team_id")
    private long documentId;

    public static NoteDocumentManager of(final long noteId, final long documentId) {
        return NoteDocumentManager.builder()
                .noteId(noteId)
                .documentId(documentId)
                .build();
    }
}
