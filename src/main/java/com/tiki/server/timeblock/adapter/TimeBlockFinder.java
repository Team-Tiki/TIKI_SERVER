package com.tiki.server.timeblock.adapter;

import static com.tiki.server.timeblock.message.ErrorCode.*;

import java.util.List;
import java.util.Optional;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.timeblock.entity.TimeBlock;
import com.tiki.server.timeblock.exception.TimeBlockException;
import com.tiki.server.timeblock.repository.TimeBlockRepository;
import com.tiki.server.timeblock.vo.TimeBlockVO;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class TimeBlockFinder {

    private final TimeBlockRepository timeBlockRepository;

    public TimeBlockVO findByIdOrElseThrow(long id) {
        return timeBlockRepository.findById(id)
                .map(TimeBlockVO::from)
                .orElseThrow(() -> new TimeBlockException(INVALID_TIME_BLOCK));
    }

    public Optional<TimeBlock> findById(final long id) {
        return timeBlockRepository.findById(id);
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
}
