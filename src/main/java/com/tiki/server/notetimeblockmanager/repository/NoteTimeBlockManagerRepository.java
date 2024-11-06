package com.tiki.server.notetimeblockmanager.repository;

import com.tiki.server.notetimeblockmanager.entity.NoteTimeBlockManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteTimeBlockManagerRepository extends JpaRepository<NoteTimeBlockManager, Long> {
}
