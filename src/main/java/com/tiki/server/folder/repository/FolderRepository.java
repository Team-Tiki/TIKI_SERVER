package com.tiki.server.folder.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiki.server.folder.entity.Folder;

public interface FolderRepository extends JpaRepository<Folder, Long> {

	List<Folder> findAllByTeamIdAndPathOrderByCreatedAtDesc(long teamId, String path);

	List<Folder> findAllByPathOrderByCreatedAtDesc(String path);

	Optional<Folder> findByIdAndTeamId(long id, long teamId);

	List<Folder> findAllByPathStartsWith(String path);
}
