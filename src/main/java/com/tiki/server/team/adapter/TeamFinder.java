package com.tiki.server.team.adapter;

import static com.tiki.server.team.message.ErrorCode.INVALID_TEAM;

import com.tiki.server.common.entity.University;
import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.team.entity.Team;
import com.tiki.server.team.exception.TeamException;
import com.tiki.server.team.repository.TeamRepository;

import com.tiki.server.team.vo.TeamVO;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RepositoryAdapter
@RequiredArgsConstructor
public class TeamFinder {

    private final TeamRepository teamRepository;

    public Team findById(long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new TeamException(INVALID_TEAM));
    }

    public List<TeamVO> findAllByUniv(University univ) {
        return teamRepository.findAllByUniv(univ)
                .map(teams -> teams.stream()
                        .map(TeamVO::from)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

}
