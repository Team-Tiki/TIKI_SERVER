package com.tiki.server.notetimeblockmanager.repository;

import com.tiki.server.notetimeblockmanager.entity.NTBManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NTBRepository extends JpaRepository<NTBManager, Long> {

    void deleteAllByNoteId(final long noteId);

    void deleteByNoteIdAndTimeBlockId(final long noteId, final long timeBlockId);

    List<NTBManager> findAllByNoteId(final long noteId);

    List<NTBManager> findAllByTimeBlockId(final long timeBlockId);

    void deleteAllByTimeBlockId(final long timeBlockId);
}
