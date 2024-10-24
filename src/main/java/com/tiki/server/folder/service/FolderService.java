package com.tiki.server.folder.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiki.server.folder.adapter.FolderFinder;
import com.tiki.server.folder.adapter.FolderSaver;
import com.tiki.server.folder.dto.request.FolderCreateRequest;
import com.tiki.server.folder.dto.response.FolderCreateResponse;
import com.tiki.server.folder.entity.Folder;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FolderService {

	private final FolderFinder folderFinder;
	private final FolderSaver folderSaver;
	private final MemberTeamManagerFinder memberTeamManagerFinder;

	@Transactional
	public FolderCreateResponse create(long memberId, long teamId, FolderCreateRequest request) {
		// 같은 레벨 파일명 중복 방지 로직 추가 필요
		memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
		Folder parentFolder = getFolder(request.parentId());
		Folder folder = folderSaver.save(new Folder(request.name(), parentFolder, teamId));
		return FolderCreateResponse.from(folder.getId());
	}

	private Folder getFolder(Long folderId) {
		if (folderId == null) {
			return null;
		}
		return folderFinder.findById(folderId);
	}
}
