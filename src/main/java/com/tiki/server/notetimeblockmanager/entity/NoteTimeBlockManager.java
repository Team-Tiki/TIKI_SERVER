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

    @JoinColumn(name = "note_id")
    private long noteId;

    @JoinColumn(name = "time_block_id")
    private long timeBlockId;

    public static NoteTimeBlockManager of(final long noteId, final long timeBlockId) {
        return NoteTimeBlockManager.builder()
                .noteId(noteId)
                .timeBlockId(timeBlockId)
                .build();
    }
}
