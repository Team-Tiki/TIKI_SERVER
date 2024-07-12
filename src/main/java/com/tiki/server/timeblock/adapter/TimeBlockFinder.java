package com.tiki.server.timeblock.adapter;

import java.util.List;

import com.tiki.server.common.entity.Position;
import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.team.entity.Team;
import com.tiki.server.timeblock.repository.TimeBlockRepository;
import com.tiki.server.timeblock.vo.TimeBlockVO;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class TimeBlockFinder {

	private final TimeBlockRepository timeBlockRepository;

	public List<TimeBlockVO> findByTeamAndAccessiblePosition(Team team, Position accessiblePosition) {
		return timeBlockRepository.findByTeamAndAccessiblePosition(team, accessiblePosition).stream()
			.map(TimeBlockVO::from)
			.toList();
	}
}
