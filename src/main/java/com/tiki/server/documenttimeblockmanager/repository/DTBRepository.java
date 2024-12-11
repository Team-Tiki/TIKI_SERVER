package com.tiki.server.documenttimeblockmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiki.server.documenttimeblockmanager.entity.DTBManager;

public interface DTBRepository extends JpaRepository<DTBManager, Long> {
	void deleteAllByTimeBlockId(long timeBlockId);

	void deleteAllByDocumentIdIn(List<Long> documentIds);
}
