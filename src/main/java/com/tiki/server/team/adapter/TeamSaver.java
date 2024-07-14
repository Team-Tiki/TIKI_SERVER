package com.tiki.server.team.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.team.entity.Team;
import com.tiki.server.team.repository.TeamRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class TeamSaver {

	private final TeamRepository teamRepository;

	public Team save(Team team) {
		return teamRepository.save(team);
	}
}
