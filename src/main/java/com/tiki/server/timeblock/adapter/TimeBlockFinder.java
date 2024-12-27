package com.tiki.server.timeblock.adapter;

import static com.tiki.server.timeblock.message.ErrorCode.*;

import java.util.List;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.timeblock.entity.TimeBlock;
import com.tiki.server.timeblock.exception.TimeBlockException;
import com.tiki.server.timeblock.repository.TimeBlockRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class TimeBlockFinder {

	private final TimeBlockRepository timeBlockRepository;

	public TimeBlock findById(final long id) {
		return timeBlockRepository.findById(id)
			.orElseThrow(() -> new TimeBlockException(INVALID_TIME_BLOCK));
	}

	public TimeBlock findByIdAndTeamId(final long id, final long teamId) {
		return timeBlockRepository.findByIdAndTeamId(id, teamId)
			.orElseThrow(() -> new TimeBlockException(INVALID_TIME_BLOCK));
	}

	public List<TimeBlock> findByTeamAndAccessiblePositionAndDate(
		final long teamId,
		final String accessiblePosition,
		final String date
	) {
		return timeBlockRepository.findByTeamAndAccessiblePositionAndDate(teamId, accessiblePosition, date).stream()
			.toList();
	}

	public boolean existsById(final Long timeBlockId) {
		return timeBlockRepository.existsById(timeBlockId);
	}
}
