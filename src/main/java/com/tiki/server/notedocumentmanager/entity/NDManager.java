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
@Table(name = "note_document_manager")
public class NDManager extends BaseTime {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "manager_id")
    private Long id;

    @Column(nullable = false)
    private long noteId;

    @Column(nullable = false)
    private long documentId;

    public static NDManager of(final long noteId, final long documentId) {
        return NDManager.builder()
                .noteId(noteId)
                .documentId(documentId)
                .build();
    }
}
