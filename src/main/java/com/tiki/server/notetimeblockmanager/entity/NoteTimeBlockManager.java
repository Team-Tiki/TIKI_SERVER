package com.tiki.server.notetimeblockmanager.entity;

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
public class NoteTimeBlockManager extends BaseTime {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "manager_id")
    private Long id;

    @Column(nullable = false)
    private long noteId;

    @Column(nullable = false)
    private long timeBlockId;

    public static NoteTimeBlockManager of(final long noteId, final long timeBlockId) {
        return NoteTimeBlockManager.builder()
                .noteId(noteId)
                .timeBlockId(timeBlockId)
                .build();
    }
}
