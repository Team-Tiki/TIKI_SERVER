package com.tiki.server.folder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiki.server.folder.entity.Folder;

public interface FolderRepository extends JpaRepository<Folder, Long> {
}
