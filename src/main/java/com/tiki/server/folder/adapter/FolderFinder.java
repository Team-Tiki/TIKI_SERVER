package com.tiki.server.folder.adapter;

import static com.tiki.server.folder.constant.Constant.ROOT_PATH;
import static com.tiki.server.folder.message.ErrorCode.INVALID_FOLDER;

import java.util.List;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.folder.entity.Folder;
import com.tiki.server.folder.exception.FolderException;
import com.tiki.server.folder.repository.FolderRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class FolderFinder {

	private final FolderRepository folderRepository;

	public Folder findById(final long id) {
		return folderRepository.findById(id)
			.orElseThrow(() -> new FolderException(INVALID_FOLDER));
	}

	public List<Folder> findByTeamIdAndPath(final long teamId, final String path) {
		if (path.equals(ROOT_PATH)) {
			return folderRepository.findAllByTeamIdAndPathOrderByCreatedAtDesc(teamId, path);
		}
		return folderRepository.findAllByPathOrderByCreatedAtDesc(path);
	}

	public List<Folder> findAllStartWithPath(final String path) {
		return folderRepository.findAllByPathStartsWith(path);
	}
}
