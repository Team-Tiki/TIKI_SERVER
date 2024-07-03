package com.tiki.server.team.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.team.repository.TeamRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class TeamFinder {

	private final TeamRepository teamRepository;
}
