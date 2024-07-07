package com.tiki.server.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiki.server.document.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
