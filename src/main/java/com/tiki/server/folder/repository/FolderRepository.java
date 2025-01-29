package com.tiki.server.folder.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiki.server.folder.entity.Folder;

public interface FolderRepository extends JpaRepository<Folder, Long> {

	List<Folder> findAllByTeamIdAndPathOrderByCreatedAtDesc(final long teamId, final String path);

	List<Folder> findAllByPathOrderByCreatedAtDesc(final String path);

	Optional<Folder> findByIdAndTeamId(final long id, final long teamId);

	List<Folder> findAllByPathStartsWith(final String path);

	void deleteAllByTeamId(final long teamId);
}
