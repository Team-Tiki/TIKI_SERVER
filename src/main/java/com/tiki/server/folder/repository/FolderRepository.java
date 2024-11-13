package com.tiki.server.folder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiki.server.folder.entity.Folder;

public interface FolderRepository extends JpaRepository<Folder, Long> {
	List<Folder> findAllByTeamIdAndPathOrderByCreatedAtDesc(long teamId, String path);
	List<Folder> findAllByPathOrderByCreatedAtDesc(String path);
}
