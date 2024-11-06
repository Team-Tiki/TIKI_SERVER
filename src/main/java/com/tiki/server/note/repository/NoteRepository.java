package com.tiki.server.note.repository;

import com.tiki.server.note.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findAllByTeamId(final long teamId);
}
