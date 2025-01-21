package com.tiki.server.document.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiki.server.document.entity.DeletedDocument;

public interface DeletedDocumentRepository extends JpaRepository<DeletedDocument, Long> {

	Optional<DeletedDocument> findByIdAndTeamId(final long id, final long teamId);

	List<DeletedDocument> findAllByTeamId(final long teamId);
}
