package com.tiki.server.document.service;

import static com.tiki.server.document.message.ErrorCode.INVALID_DOCUMENT;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiki.server.common.entity.Position;
import com.tiki.server.document.adapter.DocumentDeleter;
import com.tiki.server.document.adapter.DocumentFinder;
import com.tiki.server.document.dto.response.DocumentsGetResponse;
import com.tiki.server.document.entity.Document;
import com.tiki.server.document.exception.DocumentException;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import com.tiki.server.memberteammanager.entity.MemberTeamManager;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DocumentService {

	private final DocumentFinder documentFinder;
	private final DocumentDeleter documentDeleter;
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
		checkDocumentExist(document);
		memberTeamManager.checkMemberAccessible(document.getTimeBlock().getAccessiblePosition());
		documentDeleter.delete(document);
	}

	private DocumentsGetResponse getAllDocumentsByType(long teamId, Position accessiblePosition) {
		List<Document> documents = documentFinder.findAllByTeamIdAndAccessiblePosition(teamId, accessiblePosition);
		return DocumentsGetResponse.from(documents);
	}

	private void checkDocumentExist(Document document) {
		if (Objects.isNull(document)) {
			throw new DocumentException(INVALID_DOCUMENT);
		}
	}
}
