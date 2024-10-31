package com.tiki.server.note.repository;

import com.tiki.server.note.entity.NoteFree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteFreeRepository extends JpaRepository<NoteFree, Long> {
}
