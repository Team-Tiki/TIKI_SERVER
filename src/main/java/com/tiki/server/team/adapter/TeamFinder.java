package com.tiki.server.team.adapter;

import static com.tiki.server.team.message.ErrorCode.INVALID_TEAM;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.memberteammanager.dto.response.BelongTeamsResponse;
import com.tiki.server.team.entity.Team;
import com.tiki.server.team.exception.TeamException;
import com.tiki.server.team.repository.TeamRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

@RepositoryAdapter
@RequiredArgsConstructor
public class TeamFinder {

	private final TeamRepository teamRepository;

	public Team findById(long teamId) {
		return teamRepository.findById(teamId)
			.orElseThrow(() -> new TeamException(INVALID_TEAM));
	}

	public Optional<Team> findBelongTeamByMemberId(long memberId){
		return teamRepository.findAllBelongTeamByMemberId(memberId);
	}
}
