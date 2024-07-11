package com.tiki.server.timeblock.service;

import static com.tiki.server.timeblock.message.ErrorCode.INVALID_AUTHORIZATION;
import static com.tiki.server.timeblock.message.ErrorCode.INVALID_TYPE;
import static com.tiki.server.timeblock.constant.TimeBlockConstant.EXECUTIVE;
import static com.tiki.server.timeblock.constant.TimeBlockConstant.MEMBER;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiki.server.common.entity.Position;
import com.tiki.server.document.adapter.DocumentSaver;
import com.tiki.server.document.entity.Document;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import com.tiki.server.team.adapter.TeamFinder;
import com.tiki.server.team.entity.Team;
import com.tiki.server.timeblock.adapter.TimeBlockSaver;
import com.tiki.server.timeblock.dto.request.TimeBlockCreateRequest;
import com.tiki.server.timeblock.dto.response.TimeBlockCreateResponse;
import com.tiki.server.timeblock.entity.TimeBlock;
import com.tiki.server.timeblock.exception.TimeBlockException;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TimeBlockService {

	private final TeamFinder teamFinder;
	private final MemberTeamManagerFinder memberTeamManagerFinder;
	private final TimeBlockSaver timeBlockSaver;
	private final DocumentSaver documentSaver;

	@Transactional
	public TimeBlockCreateResponse createTimeBlock(
		long memberId,
		long teamId,
		String type,
		TimeBlockCreateRequest request
	) {
		val team = teamFinder.findById(teamId);
		val position = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId).getPosition();
		return switch (type) {
			case EXECUTIVE -> createTimeBlockByType(team, Position.EXECUTIVE, position, request);
			case MEMBER -> createTimeBlockByType(team, Position.MEMBER, position, request);
			default -> throw new TimeBlockException(INVALID_TYPE);
		};
	}

	private TimeBlockCreateResponse createTimeBlockByType(
		Team team,
		Position accessiblePosition,
		Position memberPosition,
		TimeBlockCreateRequest request
	) {
		checkMemberAccessible(accessiblePosition, memberPosition);
		val timeBlock = saveTimeBlock(team, accessiblePosition, request);
		val timeBlockId = timeBlock.getId();
		saveDocuments(request.files(), timeBlock);
		return TimeBlockCreateResponse.of(timeBlockId);
	}

	private void checkMemberAccessible(Position accessiblePosition, Position memberPosition) {
		if (accessiblePosition.getAuthorization() < memberPosition.getAuthorization()) {
			throw new TimeBlockException(INVALID_AUTHORIZATION);
		}
	}

	private TimeBlock saveTimeBlock(Team team, Position accessiblePosition, TimeBlockCreateRequest request) {
		return timeBlockSaver.save(createTimeBlock(team, accessiblePosition, request));
	}

	private TimeBlock createTimeBlock(Team team, Position accessiblePosition, TimeBlockCreateRequest request) {
		return TimeBlock.of(team, accessiblePosition, request);
	}

	private void saveDocuments(Map<String, String> files, TimeBlock timeBlock) {
		files.forEach((fileName, fileUrl) -> documentSaver.save(createDocument(fileName, fileUrl, timeBlock)));
	}

	private Document createDocument(String fileName, String fileUrl, TimeBlock timeBlock) {
		return Document.of(fileName, fileUrl, timeBlock);
	}
}
