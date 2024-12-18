package com.tiki.server.drive.service;

import static com.tiki.server.folder.constant.Constant.ROOT_PATH;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiki.server.document.adapter.DocumentFinder;
import com.tiki.server.document.entity.Document;
import com.tiki.server.drive.dto.DriveGetResponse;
import com.tiki.server.folder.adapter.FolderFinder;
import com.tiki.server.folder.entity.Folder;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DriveService {

	private final MemberTeamManagerFinder memberTeamManagerFinder;
	private final DocumentFinder documentFinder;
	private final FolderFinder folderFinder;

	public DriveGetResponse getDrive(final long memberId, final long teamId, final Long folderId) {
		memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
		List<Document> documents = documentFinder.findByTeamIdAndFolderId(teamId, folderId);
		Folder folder = getFolder(teamId, folderId);
		String path = getChildFolderPath(folder);
		List<Folder> folders = folderFinder.findByTeamIdAndPath(teamId, path);
		return DriveGetResponse.of(documents, folders);
	}

	private Folder getFolder(final long teamId, final Long folderId) {
		if (folderId == null) {
			return null;
		}
		Folder folder = folderFinder.findById(folderId);
		folder.validateTeamId(teamId);
		return folder;
	}

	private String getChildFolderPath(final Folder folder) {
		if (folder == null) {
			return ROOT_PATH;
		}
		return folder.getChildPath();
	}
}
