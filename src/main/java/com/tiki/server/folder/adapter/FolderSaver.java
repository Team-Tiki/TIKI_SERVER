package com.tiki.server.folder.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.folder.entity.Folder;
import com.tiki.server.folder.repository.FolderRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class FolderSaver {

	private final FolderRepository folderRepository;

	public Folder save(Folder folder) {
		return folderRepository.save(folder);
	}
}
