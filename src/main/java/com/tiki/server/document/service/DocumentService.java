package com.tiki.server.document.service;

import static com.tiki.server.document.message.ErrorCode.DOCUMENT_NAME_DUPLICATE;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiki.server.common.entity.Position;
import com.tiki.server.document.adapter.DeletedDocumentAdapter;
import com.tiki.server.document.adapter.DocumentDeleter;
import com.tiki.server.document.adapter.DocumentFinder;
import com.tiki.server.document.adapter.DocumentSaver;
import com.tiki.server.document.dto.request.DocumentCreateRequest;
import com.tiki.server.document.dto.request.DocumentsCreateRequest;
import com.tiki.server.document.dto.response.DeletedDocumentsGetResponse;
import com.tiki.server.document.dto.response.DocumentsGetResponse;
import com.tiki.server.document.entity.DeletedDocument;
import com.tiki.server.document.entity.Document;
import com.tiki.server.document.exception.DocumentException;
import com.tiki.server.external.util.S3Handler;
import com.tiki.server.folder.adapter.FolderFinder;
import com.tiki.server.folder.entity.Folder;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import com.tiki.server.memberteammanager.entity.MemberTeamManager;
import com.tiki.server.team.adapter.TeamFinder;
import com.tiki.server.team.entity.Team;

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
	private final DeletedDocumentAdapter deletedDocumentAdapter;
	private final TeamFinder teamFinder;
	private final S3Handler s3Handler;

	public DocumentsGetResponse getAllDocuments(final long memberId, final long teamId, final String type) {
		MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
		Position accessiblePosition = Position.getAccessiblePosition(type);
		memberTeamManager.checkMemberAccessible(accessiblePosition);
		return getAllDocumentsByType(teamId, accessiblePosition);
	}

	@Transactional
	public void deleteDocument(final long memberId, final long teamId, final long documentId) {
		MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
		Document document = documentFinder.findByIdWithTimeBlock(documentId);
		memberTeamManager.checkMemberAccessible(document.getTimeBlock().getAccessiblePosition());
		documentDeleter.delete(document);
	}

	@Transactional
	public void createDocuments(final long memberId, final long teamId,
			final Long folderId, final DocumentsCreateRequest request) {
		memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
		validateFolder(folderId, teamId);
		validateFileName(folderId, teamId, request);
		saveDocuments(teamId, folderId, request);
	}

	public DocumentsGetResponse get(final long memberId, final long teamId, final Long folderId) {
		memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
		List<Document> documents = documentFinder.findByTeamIdAndFolderId(teamId, folderId);
		return DocumentsGetResponse.from(documents);
	}

	@Transactional
	public void delete(final long memberId, final long teamId, final List<Long> documentIds) {
		memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
		List<Document> documents = documentFinder.findAllByIdAndTeamId(documentIds, teamId);
		deletedDocumentAdapter.save(documents);
		documentDeleter.deleteAll(documents);
	}

	@Transactional
	public void deleteTrash(final long memberId, final long teamId, final List<Long> documentIds) {
		memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
		List<DeletedDocument> deletedDocuments = deletedDocumentAdapter.get(documentIds, teamId);
		restoreTeamUsage(teamId, deletedDocuments);
		for (DeletedDocument deletedDocument : deletedDocuments) {
			s3Handler.deleteFile(deletedDocument.getFileName());
		}
		deletedDocumentAdapter.deleteAll(deletedDocuments);
	}

	@Transactional
	public void restore(final long memberId, final long teamId, final List<Long> documentIds) {
		memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
		List<DeletedDocument> deletedDocuments = deletedDocumentAdapter.get(documentIds, teamId);
		documentSaver.restore(deletedDocuments);
		deletedDocumentAdapter.deleteAll(deletedDocuments);
	}

	public DeletedDocumentsGetResponse getTrash(final long memberId, final long teamId) {
		memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
		List<DeletedDocument> deletedDocuments = deletedDocumentAdapter.get(teamId);
		return DeletedDocumentsGetResponse.from(deletedDocuments);
	}

	private DocumentsGetResponse getAllDocumentsByType(final long teamId, final Position accessiblePosition) {
		List<Document> documents = documentFinder.findAllByTeamIdAndAccessiblePosition(teamId, accessiblePosition);
		return DocumentsGetResponse.from(documents);
	}

	private void validateFolder(final Long folderId, final long teamId) {
		if (folderId == null) {
			return;
		}
		Folder folder = folderFinder.findById(folderId);
		folder.validateTeamId(teamId);
	}

	private void validateFileName(final Long folderId, final long teamId, final DocumentsCreateRequest request) {
		List<Document> documents = documentFinder.findByTeamIdAndFolderId(teamId, folderId);
		documents.forEach(document -> checkFileNameIsDuplicated(document.getFileName(), request));
	}

	private void checkFileNameIsDuplicated(final String fileName, final DocumentsCreateRequest request) {
		if (request.documents().stream().anyMatch(document -> document.fileName().equals(fileName))) {
			throw new DocumentException(DOCUMENT_NAME_DUPLICATE);
		}
	}

	private void saveDocuments(final long teamId, final Long folderId, final DocumentsCreateRequest request) {
		Team team = teamFinder.findById(teamId);
		request.documents().forEach(document -> {
			team.addUsage(document.capacity());
			saveDocument(teamId, folderId, document);
		});
	}

	private void saveDocument(final long teamId, final Long folderId, final DocumentCreateRequest request) {
		Document document = Document.of(
			request.fileName(), request.fileUrl(), request.capacity(), teamId, folderId);
		documentSaver.save(document);
	}

	private void restoreTeamUsage(final long teamId, final List<DeletedDocument> deletedDocuments) {
		Team team = teamFinder.findById(teamId);
		team.restoreUsage(deletedDocuments.stream().mapToDouble(DeletedDocument::getCapacity).sum());
	}
}
