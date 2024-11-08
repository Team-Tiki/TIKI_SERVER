package com.tiki.server.notetimeblockmanager.repository;

import com.tiki.server.notetimeblockmanager.entity.NoteTimeBlockManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteTimeBlockManagerRepository extends JpaRepository<NoteTimeBlockManager, Long> {

    public void deleteAllByNoteId(long noteId);

    public List<NoteTimeBlockManager> findAllByNoteId(final long NoteId);
}
