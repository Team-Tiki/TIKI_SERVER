package com.tiki.server.folder.adapter;

import java.util.List;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.folder.entity.Folder;
import com.tiki.server.folder.repository.FolderRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class FolderDeleter {

	private final FolderRepository folderRepository;

	public void deleteAll(final List<Folder> folders) {
		folderRepository.deleteAll(folders);
	}
}
