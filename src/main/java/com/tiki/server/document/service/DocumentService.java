package com.tiki.server.document.service;

import static com.tiki.server.document.message.ErrorCode.INVALID_AUTHORIZATION;
import static com.tiki.server.timeblock.constant.TimeBlockConstant.EXECUTIVE;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiki.server.common.entity.Position;
import com.tiki.server.document.adapter.DocumentFinder;
import com.tiki.server.document.dto.response.DocumentsGetResponse;
import com.tiki.server.document.exception.DocumentException;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DocumentService {

	private final DocumentFinder documentFinder;
	private final MemberTeamManagerFinder memberTeamManagerFinder;

	public DocumentsGetResponse getAllDocuments(long memberId, long teamId, String type) {
		val position = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId).getPosition();
		switch (type) {
			case EXECUTIVE ->
		}
	}

	private void checkMemberAccessible(Position accessiblePosition, Position memberPosition) {
		if (accessiblePosition.getAuthorization() < memberPosition.getAuthorization()) {
			throw new DocumentException(INVALID_AUTHORIZATION);
		}
	}
}
