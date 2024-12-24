package com.tiki.server.notedocumentmanager.repository;

import com.tiki.server.notedocumentmanager.entity.NDManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NDManagerRepository extends JpaRepository<NDManager, Long> {

    void deleteAllByNoteId(final long noteId);

    void deleteByNoteIdAndDocumentId(final long noteId, final long documentId);

    List<NDManager> findAllByNoteId(final long noteId);
}
