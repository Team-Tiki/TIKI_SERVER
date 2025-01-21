package com.tiki.server.document.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiki.server.document.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
	List<Document> findAllByFolderId(final long folderId);

	List<Document> findAllByTeamId(final long teamId);

	List<Document> findAllByTeamIdAndFolderIdOrderByCreatedAtDesc(final long teamId, final Long folderId);

	Optional<Document> findByIdAndTeamId(final long id, final long teamId);
}
