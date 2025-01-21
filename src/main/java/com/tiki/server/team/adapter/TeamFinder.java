package com.tiki.server.team.adapter;

import static com.tiki.server.team.message.ErrorCode.INVALID_TEAM;

import com.tiki.server.common.entity.University;
import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.team.entity.Team;
import com.tiki.server.team.exception.TeamException;
import com.tiki.server.team.repository.TeamRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RepositoryAdapter
@RequiredArgsConstructor
public class TeamFinder {

    private final TeamRepository teamRepository;

    public Team findById(final long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new TeamException(INVALID_TEAM));
    }

    public List<Team> findAllByUniv(final University univ) {
        return teamRepository.findAllByUniv(univ);
    }
}
