package com.tiki.server.document.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiki.server.document.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
	List<Document> findAllByFolderId(long folderId);

	List<Document> findAllByTeamId(long teamId);

	List<Document> findAllByTeamIdAndFolderIdOrderByCreatedAtDesc(long teamId, Long folderId);

	Optional<Document> findByIdAndTeamId(long id, long teamId);
}
