package com.tiki.server.documenttimeblockmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiki.server.documenttimeblockmanager.entity.DTBManager;

public interface DTBRepository extends JpaRepository<DTBManager, Long> {
	List<DTBManager> findAllByTimeBlockId(final long timeBlockId);

	List<DTBManager> findAllByIdIn(final List<Long> ids);

	void deleteAllByTimeBlockId(final long timeBlockId);

	void deleteAllByDocumentIdIn(final List<Long> documentIds);
}
