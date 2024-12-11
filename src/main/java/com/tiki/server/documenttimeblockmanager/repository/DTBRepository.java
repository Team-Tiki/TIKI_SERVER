package com.tiki.server.documenttimeblockmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiki.server.documenttimeblockmanager.entity.DTBManager;

public interface DTBRepository extends JpaRepository<DTBManager, Long> {
	List<DTBManager> findAllByTimeBlockId(long timeBlockId);

	List<DTBManager> findAllByIdIn(List<Long> ids);

	void deleteAllByTimeBlockId(long timeBlockId);

	void deleteAllByDocumentIdIn(List<Long> documentIds);
}
