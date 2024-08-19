package com.tiki.server.timeblock.service;

import static com.tiki.server.timeblock.message.ErrorCode.INVALID_TYPE;
import static com.tiki.server.timeblock.constant.TimeBlockConstant.EXECUTIVE;
import static com.tiki.server.timeblock.constant.TimeBlockConstant.MEMBER;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiki.server.common.entity.Position;
import com.tiki.server.document.adapter.DocumentDeleter;
import com.tiki.server.document.adapter.DocumentFinder;
import com.tiki.server.document.adapter.DocumentSaver;
import com.tiki.server.document.entity.Document;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import com.tiki.server.team.adapter.TeamFinder;
import com.tiki.server.team.entity.Team;
import com.tiki.server.timeblock.adapter.TimeBlockDeleter;
import com.tiki.server.timeblock.adapter.TimeBlockFinder;
import com.tiki.server.timeblock.adapter.TimeBlockSaver;
import com.tiki.server.timeblock.dto.request.TimeBlockCreateRequest;
import com.tiki.server.timeblock.dto.response.TimeBlockCreateResponse;
import com.tiki.server.timeblock.dto.response.TimeBlockDetailGetResponse;
import com.tiki.server.timeblock.dto.response.TimelineGetResponse;
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
	private final TimeBlockFinder timeBlockFinder;
	private final TimeBlockDeleter timeBlockDeleter;
	private final DocumentSaver documentSaver;
	private final DocumentFinder documentFinder;
	private final DocumentDeleter documentDeleter;

	@Transactional
	public TimeBlockCreateResponse createTimeBlock(
		long memberId,
		long teamId,
		String type,
		TimeBlockCreateRequest request
	) {
		val team = teamFinder.findById(teamId);
		val memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
		val accessiblePosition = getAccessiblePosition(type);
		memberTeamManager.checkMemberAccessible(accessiblePosition);
		val timeBlock = saveTimeBlock(team, accessiblePosition, request);
		saveDocuments(request.files(), timeBlock);
		return TimeBlockCreateResponse.of(timeBlock.getId());
	}

	public TimelineGetResponse getTimeline(long memberId, long teamId, String type, String date) {
		val team = teamFinder.findById(teamId);
		val memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
		val accessiblePosition = getAccessiblePosition(type);
		memberTeamManager.checkMemberAccessible(accessiblePosition);
		val timeBlocks = timeBlockFinder.findByTeamAndAccessiblePositionAndDate(
			team.getId(), accessiblePosition.name(), date);
		return TimelineGetResponse.from(timeBlocks);
	}

	public TimeBlockDetailGetResponse getTimeBlockDetail(long memberId, long teamId, long timeBlockId) {
		val memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
		val timeBlock = timeBlockFinder.findById(timeBlockId);
		memberTeamManager.checkMemberAccessible(timeBlock.accessiblePosition());
		val documents = documentFinder.findAllByTimeBlockId(timeBlockId);
		return TimeBlockDetailGetResponse.from(documents);
	}

	@Transactional
	public void deleteTimeBlock(long memberId, long teamId, long timeBlockId) {
		val memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
		val timeBlock = timeBlockFinder.findById(timeBlockId);
		memberTeamManager.checkMemberAccessible(timeBlock.accessiblePosition());
		val documents = documentFinder.findAllByTimeBlockId(timeBlockId);
		documentDeleter.deleteAllById(documents);
		timeBlockDeleter.deleteById(timeBlock.timeBlockId());
	}

	private Position getAccessiblePosition(String type) {
		return switch (type) {
			case EXECUTIVE -> Position.EXECUTIVE;
			case MEMBER -> Position.MEMBER;
			default -> throw new TimeBlockException(INVALID_TYPE);
		};
	}

	private TimeBlock saveTimeBlock(Team team, Position accessiblePosition, TimeBlockCreateRequest request) {
		return timeBlockSaver.save(TimeBlock.of(team, accessiblePosition, request));
	}

	private void saveDocuments(Map<String, String> files, TimeBlock timeBlock) {
		files.forEach((fileName, fileUrl) -> documentSaver.save(Document.of(fileName, fileUrl, timeBlock)));
	}
}
