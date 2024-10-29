package com.tiki.server.document.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiki.server.common.entity.Position;
import com.tiki.server.document.adapter.DocumentDeleter;
import com.tiki.server.document.adapter.DocumentFinder;
import com.tiki.server.document.adapter.DocumentSaver;
import com.tiki.server.document.dto.request.DocumentCreateRequest;
import com.tiki.server.document.dto.request.DocumentsCreateRequest;
import com.tiki.server.document.dto.response.DocumentsCreateResponse;
import com.tiki.server.document.dto.response.DocumentsGetResponse;
import com.tiki.server.document.entity.Document;
import com.tiki.server.folder.adapter.FolderFinder;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import com.tiki.server.memberteammanager.entity.MemberTeamManager;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DocumentService {

	private final DocumentSaver documentSaver;
	private final DocumentFinder documentFinder;
	private final DocumentDeleter documentDeleter;
	private final FolderFinder folderFinder;
	private final MemberTeamManagerFinder memberTeamManagerFinder;

	public DocumentsGetResponse getAllDocuments(long memberId, long teamId, String type) {
		MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
		Position accessiblePosition = Position.getAccessiblePosition(type);
		memberTeamManager.checkMemberAccessible(accessiblePosition);
		return getAllDocumentsByType(teamId, accessiblePosition);
	}

	@Transactional
	public void deleteDocument(long memberId, long teamId, long documentId) {
		MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
		Document document = documentFinder.findByIdWithTimeBlock(documentId);
		memberTeamManager.checkMemberAccessible(document.getTimeBlock().getAccessiblePosition());
		documentDeleter.delete(document);
	}

	@Transactional
	public DocumentsCreateResponse createDocuments(long memberId, long teamId, DocumentsCreateRequest request) {
		memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
		checkFolderIsExist(request.folderId());
		List<Long> documentIds = request.documents().stream()
			.map(document -> saveDocument(teamId, request.folderId(), document).getId())
			.toList();
		return DocumentsCreateResponse.from(documentIds);
	}

	private DocumentsGetResponse getAllDocumentsByType(long teamId, Position accessiblePosition) {
		List<Document> documents = documentFinder.findAllByTeamIdAndAccessiblePosition(teamId, accessiblePosition);
		return DocumentsGetResponse.from(documents);
	}

	private void checkFolderIsExist(Long folderId) {
		if (folderId == null) {
			return;
		}
		folderFinder.findById(folderId);
	}

	private Document saveDocument(long teamId, Long folderId, DocumentCreateRequest request) {
		Document document = Document.of(
			request.fileName(), request.fileUrl(), request.capacity(), teamId, folderId);
		return documentSaver.save(document);
	}
}
