package com.tiki.server.note.repository;

import com.tiki.server.note.entity.NoteTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteTemplateRepository extends JpaRepository<NoteTemplate,Long> {
}
