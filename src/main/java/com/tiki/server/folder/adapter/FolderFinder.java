package com.tiki.server.folder.adapter;

import static com.tiki.server.folder.message.ErrorCode.INVALID_FOLDER;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.folder.entity.Folder;
import com.tiki.server.folder.exception.FolderException;
import com.tiki.server.folder.repository.FolderRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class FolderFinder {

	private final FolderRepository folderRepository;

	public Folder findById(long id) {
		return folderRepository.findById(id)
			.orElseThrow(() -> new FolderException(INVALID_FOLDER));
	}
}
