package com.tiki.server.document.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiki.server.document.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
	List<Document> findAllByTimeBlockId(long timeBlockId);
}
