package com.tiki.server.document.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tiki.server.common.entity.Position;
import com.tiki.server.document.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
	List<Document> findAllByTimeBlockId(long timeBlockId);

	@Query("select d from Document d join d.timeBlock t "
		+ "where t.team.id = :teamId and t.accessiblePosition = :position order by d.createdAt asc")
	List<Document> findAllByTeamIdAndAccessiblePosition(long teamId, Position position);

	@Query("select d from Document d join fetch d.timeBlock where d.id = :documentId")
	Document findByIdWithTimeBlock(long documentId);
}
