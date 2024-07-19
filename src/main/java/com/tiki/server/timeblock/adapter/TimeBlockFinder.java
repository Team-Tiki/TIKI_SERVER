package com.tiki.server.timeblock.adapter;

import static com.tiki.server.timeblock.message.ErrorCode.*;

import java.util.List;

import com.tiki.server.common.entity.Position;
import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.team.entity.Team;
import com.tiki.server.timeblock.entity.TimeBlock;
import com.tiki.server.timeblock.exception.TimeBlockException;
import com.tiki.server.timeblock.message.ErrorCode;
import com.tiki.server.timeblock.repository.TimeBlockRepository;
import com.tiki.server.timeblock.vo.TimeBlockVO;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class TimeBlockFinder {

	private final TimeBlockRepository timeBlockRepository;

	public TimeBlockVO findById(long id) {
		return timeBlockRepository.findById(id)
			.map(TimeBlockVO::from)
			.orElseThrow(() -> new TimeBlockException(INVALID_TIME_BLOCK));
	}

	public List<TimeBlockVO> findByTeamAndAccessiblePositionAndDate(
		long teamId,
		String accessiblePosition,
		String date
	) {
		return timeBlockRepository.findByTeamAndAccessiblePositionAndDate(teamId, accessiblePosition, date).stream()
			.map(TimeBlockVO::from)
			.toList();
	}

	public List<TimeBlock> findByTeamId(long teamId) {
		return timeBlockRepository.findByTeamId(teamId);
	}
}
