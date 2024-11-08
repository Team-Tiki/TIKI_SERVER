package com.tiki.server.notedocumentmanager.repository;

import com.tiki.server.notedocumentmanager.entity.NoteDocumentManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteDocumentManagerRepository extends JpaRepository<NoteDocumentManager, Long> {

    public void deleteAllByNoteId(long noteId);

    public List<NoteDocumentManager> findAllByNoteId(final long noteId);
}
