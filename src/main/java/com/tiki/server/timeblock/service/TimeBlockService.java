package com.tiki.server.timeblock.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiki.server.member.adapter.MemberFinder;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import com.tiki.server.team.adapter.TeamFinder;
import com.tiki.server.timeblock.dto.request.TimeBlockCreationRequest;
import com.tiki.server.timeblock.dto.response.TimeBlockCreationResponse;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TimeBlockService {

	private final MemberFinder memberFinder;
	private final TeamFinder teamFinder;
	private final MemberTeamManagerFinder memberTeamManagerFinder;

	public TimeBlockCreationResponse createTimeBlock(long memberId, long teamId, String type, TimeBlockCreationRequest request) {
		val member = memberFinder.findById(memberId);
		val team = teamFinder.findById(teamId);
		val position = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId).getPosition();
	}

	private TimeBlockCreationResponse createExecutiveTimeBlock() {

	}

	private TimeBlockCreationResponse createMemberTimeBlock() {

	}
}
