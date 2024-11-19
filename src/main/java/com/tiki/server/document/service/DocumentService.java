package com.tiki.server.document.service;

import static com.tiki.server.document.message.ErrorCode.DOCUMENT_NAME_DUPLICATE;
import static com.tiki.server.document.message.ErrorCode.INVALID_AUTHORIZATION;

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
import com.tiki.server.document.exception.DocumentException;
import com.tiki.server.folder.adapter.FolderFinder;
import com.tiki.server.folder.entity.Folder;
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
		MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamIdOrElseThrow(memberId, teamId);
		Position accessiblePosition = Position.getAccessiblePosition(type);
		memberTeamManager.checkMemberAccessible(accessiblePosition);
		return getAllDocumentsByType(teamId, accessiblePosition);
	}

	@Transactional
	public void deleteDocument(long memberId, long teamId, long documentId) {
		MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamIdOrElseThrow(memberId, teamId);
		Document document = documentFinder.findByIdWithTimeBlock(documentId);
		memberTeamManager.checkMemberAccessible(document.getTimeBlock().getAccessiblePosition());
		documentDeleter.delete(document);
	}

	@Transactional
	public DocumentsCreateResponse createDocuments(long memberId, long teamId, DocumentsCreateRequest request) {
		memberTeamManagerFinder.findByMemberIdAndTeamIdOrElseThrow(memberId, teamId);
		validateFolder(request.folderId(), teamId);
		validateFileName(request.folderId(), teamId, request);
		checkFolderIsExist(request.folderId());
		List<Long> documentIds = request.documents().stream()
				.map(document -> saveDocument(teamId, request.folderId(), document).getId())
				.toList();
		return DocumentsCreateResponse.from(documentIds);
	}

	public DocumentsGetResponse get(final long memberId, final long teamId, final Long folderId) {
		memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
		List<Document> documents = documentFinder.findByTeamIdAndFolderId(teamId, folderId);
		return DocumentsGetResponse.from(documents);
	}

	private DocumentsGetResponse getAllDocumentsByType(long teamId, Position accessiblePosition) {
		List<Document> documents = documentFinder.findAllByTeamIdAndAccessiblePosition(teamId, accessiblePosition);
		return DocumentsGetResponse.from(documents);
	}

	private void validateFolder(Long folderId, long teamId) {
		if (folderId == null) {
			return;
		}
		Folder folder = folderFinder.findById(folderId);
		if (folder.getTeamId() != teamId) {
			throw new DocumentException(INVALID_AUTHORIZATION);
		}
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

	private void validateFileName(final Long folderId, final long teamId, final DocumentsCreateRequest request) {
		List<Document> documents = documentFinder.findByTeamIdAndFolderId(teamId, folderId);
		for (Document document : documents) {
			checkFileNameIsDuplicated(document.getFileName(), request);
		}
	}

	private void checkFileNameIsDuplicated(final String fileName, final DocumentsCreateRequest request) {
		if (request.documents().stream().anyMatch(document -> document.fileName().equals(fileName))) {
			throw new DocumentException(DOCUMENT_NAME_DUPLICATE);
		}
	}
}
