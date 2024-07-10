package com.tiki.server.timeblock.service;

import static com.tiki.server.timeblock.message.ErrorCode.INVALID_AUTHORIZATION;
import static com.tiki.server.timeblock.message.ErrorCode.INVALID_TYPE;
import static com.tiki.server.timeblock.constant.TimeBlockConstant.EXECUTIVE;
import static com.tiki.server.timeblock.constant.TimeBlockConstant.MEMBER;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiki.server.common.entity.Position;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import com.tiki.server.team.adapter.TeamFinder;
import com.tiki.server.team.entity.Team;
import com.tiki.server.timeblock.adapter.TimeBlockCreator;
import com.tiki.server.timeblock.dto.request.TimeBlockCreationRequest;
import com.tiki.server.timeblock.dto.response.TimeBlockCreationResponse;
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
	private final TimeBlockCreator timeBlockCreator;

	public TimeBlockCreationResponse createTimeBlock(
		long memberId,
		long teamId,
		String type,
		TimeBlockCreationRequest request
	) {
		val team = teamFinder.findById(teamId);
		val position = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId).getPosition();
		return switch (type) {
			case EXECUTIVE -> createTimeBlockByType(team, Position.EXECUTIVE, position, request);
			case MEMBER -> createTimeBlockByType(team, Position.MEMBER, position, request);
			default -> throw new TimeBlockException(INVALID_TYPE);
		};
	}

	private TimeBlockCreationResponse createTimeBlockByType(
		Team team,
		Position accessiblePosition,
		Position memberPosition,
		TimeBlockCreationRequest request
	) {
		checkMemberAccessible(accessiblePosition, memberPosition);
		val timeBlock = createTimeBlock(team, accessiblePosition, request);
		val timeBlockId = timeBlockCreator.createTimeBlock(timeBlock).getId();
		return TimeBlockCreationResponse.of(timeBlockId);
	}

	private void checkMemberAccessible(Position accessiblePosition, Position memberPosition) {
		if (accessiblePosition.getAuthorization() < memberPosition.getAuthorization()) {
			throw new TimeBlockException(INVALID_AUTHORIZATION);
		}
	}

	private TimeBlock createTimeBlock(Team team, Position accessiblePosition, TimeBlockCreationRequest request) {
		return TimeBlock.builder()
			.name(request.name())
			.color(request.color())
			.accessiblePosition(accessiblePosition)
			.startDate(request.startDate())
			.endDate(request.endDate())
			.team(team)
			.build();
	}
}
