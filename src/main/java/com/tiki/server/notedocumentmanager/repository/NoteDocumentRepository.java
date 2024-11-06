package com.tiki.server.notedocumentmanager.repository;

import com.tiki.server.notedocumentmanager.entity.NoteDocumentManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteDocumentRepository extends JpaRepository<NoteDocumentManager, Long> {
}
