package com.tiki.server.notedocumentmanager.repository;

import com.tiki.server.notedocumentmanager.entity.NoteDocumentManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteDocumentManagerRepository extends JpaRepository<NoteDocumentManager, Long> {

    void deleteAllByNoteId(final long noteId);

    void deleteByNoteIdAndDocumentId(final long noteId, final long documentId);

    List<NoteDocumentManager> findAllByNoteId(final long noteId);
}
