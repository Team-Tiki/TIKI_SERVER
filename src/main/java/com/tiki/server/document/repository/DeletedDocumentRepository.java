package com.tiki.server.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiki.server.document.entity.DeletedDocument;

public interface DeletedDocumentRepository extends JpaRepository<DeletedDocument, Long> {
}
