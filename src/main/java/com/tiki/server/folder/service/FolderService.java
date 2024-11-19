package com.tiki.server.folder.service;

import static com.tiki.server.folder.constant.Constant.ROOT_PATH;
import static com.tiki.server.folder.message.ErrorCode.FOLDER_NAME_DUPLICATE;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiki.server.folder.adapter.FolderFinder;
import com.tiki.server.folder.adapter.FolderSaver;
import com.tiki.server.folder.dto.request.FolderCreateRequest;
import com.tiki.server.folder.dto.response.FolderCreateResponse;
import com.tiki.server.folder.dto.response.FoldersGetResponse;
import com.tiki.server.folder.entity.Folder;
import com.tiki.server.folder.exception.FolderException;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FolderService {

	private final FolderFinder folderFinder;
	private final FolderSaver folderSaver;
	private final MemberTeamManagerFinder memberTeamManagerFinder;

	public FoldersGetResponse get(final long memberId, final long teamId,
			final Long folderId) {
		memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
		Folder folder = getFolder(teamId, folderId);
		String path = getFolderPath(folder);
		List<Folder> folders = folderFinder.findByTeamIdAndPath(teamId, path);
		return FoldersGetResponse.from(folders);
	}

	@Transactional
	public FolderCreateResponse create(final long memberId, final long teamId,
			final Long folderId, final FolderCreateRequest request) {
		memberTeamManagerFinder.findByMemberIdAndTeamIdOrElseThrow(memberId, teamId);
		Folder parentFolder = getFolder(teamId, folderId);
		String path = getFolderPath(parentFolder);
		validateFolderName(teamId, path, request);
		Folder folder = folderSaver.save(new Folder(request.name(), parentFolder, teamId));
		return FolderCreateResponse.from(folder.getId());
	}

	private Folder getFolder(final long teamId, final Long folderId) {
		if (folderId == null) {
			return null;
		}
		Folder folder = folderFinder.findById(folderId);
		folder.validateTeamId(teamId);
		return folder;
	}

	private String getFolderPath(final Folder folder) {
		if (folder == null) {
			return ROOT_PATH;
		}
		return folder.getChildPath();
	}

	private void validateFolderName(final long teamId, final String path, final FolderCreateRequest request) {
		List<Folder> folders = folderFinder.findByTeamIdAndPath(teamId, path);
		if (folders.stream().anyMatch(folder -> folder.getName().equals(request.name()))) {
			throw new FolderException(FOLDER_NAME_DUPLICATE);
		}
	}
}
