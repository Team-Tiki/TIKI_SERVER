package com.tiki.server.documenttimeblockmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiki.server.documenttimeblockmanager.entity.DTBManager;

public interface DTBRepository extends JpaRepository<DTBManager, Long> {
}
