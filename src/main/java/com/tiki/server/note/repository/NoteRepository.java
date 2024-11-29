package com.tiki.server.note.repository;

import com.tiki.server.note.entity.Note;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("SELECT n FROM Note n WHERE n.teamId = :teamId AND n.createdAt < :createdAt ORDER BY n.createdAt DESC")
    List<Note> findByTeamIdAndCreatedAtBeforeOrderByCreatedDesc(@Param("createdAt") LocalDateTime createdAt, Pageable pageable, long teamId);

    @Query("SELECT n FROM Note n WHERE n.teamId = :teamId AND n.createdAt > :createdAt ORDER BY n.createdAt ASC")
    List<Note> findByTeamIdAndCreatedAtAfterOrderByCreatedAtAsc(@Param("createdAt") LocalDateTime createdAt, Pageable pageable, long teamId);
}
