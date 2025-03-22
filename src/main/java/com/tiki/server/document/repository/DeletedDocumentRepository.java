package com.tiki.server.document.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tiki.server.document.entity.DeletedDocument;

public interface DeletedDocumentRepository extends JpaRepository<DeletedDocument, Long> {

	Optional<DeletedDocument> findByIdAndTeamId(final long id, final long teamId);

	List<DeletedDocument> findAllByTeamId(final long teamId);

	void deleteAllByTeamId(final long teamId);

	@Query("SELECT d FROM DeletedDocument d WHERE d.createdAt < :date")
	List<DeletedDocument> findAllByDate(final LocalDate date);
}
