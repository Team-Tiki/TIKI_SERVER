package com.tiki.server.document.service;

import static com.tiki.server.document.message.ErrorCode.INVALID_AUTHORIZATION;
import static com.tiki.server.document.message.ErrorCode.INVALID_DOCUMENT;
import static com.tiki.server.document.message.ErrorCode.INVALID_TYPE;
import static com.tiki.server.timeblock.constant.TimeBlockConstant.EXECUTIVE;
import static com.tiki.server.timeblock.constant.TimeBlockConstant.MEMBER;

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

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DocumentService {

	private final DocumentFinder documentFinder;
	private final DocumentDeleter documentDeleter;
	private final MemberTeamManagerFinder memberTeamManagerFinder;

	public DocumentsGetResponse getAllDocuments(long memberId, long teamId, String type) {
		val position = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId).getPosition();
		return switch (type) {
			case EXECUTIVE -> getAllDocumentsByType(teamId, Position.EXECUTIVE, position);
			case MEMBER -> getAllDocumentsByType(teamId, Position.MEMBER, position);
			default -> throw new DocumentException(INVALID_TYPE);
		};
	}

	@Transactional
	public void deleteDocument(long memberId, long teamId, long documentId) {
		val position = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId).getPosition();
		val document = documentFinder.findByIdWithTimeBlock(documentId);
		checkDocumentExist(document);
		checkMemberAccessible(document.getTimeBlock().getAccessiblePosition(), position);
		documentDeleter.delete(document);
	}

	private DocumentsGetResponse getAllDocumentsByType(
		long teamId,
		Position accessiblePosition,
		Position memberPosition
	) {
		checkMemberAccessible(accessiblePosition, memberPosition);
		val documents = documentFinder.findAllByTeamIdAndAccessiblePosition(teamId, accessiblePosition);
		return DocumentsGetResponse.from(documents);
	}

	private void checkDocumentExist(Document document) {
		if (Objects.isNull(document)) {
			throw new DocumentException(INVALID_DOCUMENT);
		}
	}

	private void checkMemberAccessible(Position accessiblePosition, Position memberPosition) {
		if (accessiblePosition.getAuthorization() < memberPosition.getAuthorization()) {
			throw new DocumentException(INVALID_AUTHORIZATION);
		}
	}
}
