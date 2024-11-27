package com.tiki.server.document.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tiki.server.common.entity.Position;
import com.tiki.server.document.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

	List<Document> findAllByTimeBlockId(long timeBlockId);

	List<Document> findAllByFolderId(long folderId);

	@Query("select d from Document d join fetch d.timeBlock t "
		+ "where t.team.id = :teamId and t.accessiblePosition = :position order by d.createdAt asc")
	List<Document> findAllByTeamIdAndAccessiblePosition(long teamId, Position position);

	@Query("select d from Document d join d.timeBlock t where t.team.id = :teamId")
	List<Document> findAllByTeamId(long teamId);

	@Query("select d from Document d join fetch d.timeBlock where d.id = :documentId")
	Document findByIdWithTimeBlock(long documentId);

	void deleteAllByTimeBlockId(long timeBlockId);

	List<Document> findAllByTeamIdAndFolderIdOrderByCreatedAtDesc(long teamId, Long folderId);

	Optional<Document> findByIdAndTeamId(long id, long teamId);
}
