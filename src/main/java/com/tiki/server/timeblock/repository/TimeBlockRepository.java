package com.tiki.server.timeblock.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tiki.server.timeblock.entity.TimeBlock;

public interface TimeBlockRepository extends JpaRepository<TimeBlock, Long> {

    Optional<TimeBlock> findByIdAndTeamId(final long id, final long teamId);

    void deleteAllByTeamId(final long teamId);

    @Query(value = "select * from time_block "
            + "where team_id = :teamId and accessible_position = :position and "
            + "(to_char(start_date, 'YYYY-MM') <= :date and to_char(end_date, 'YYYY-MM') >= :date) "
            + "order by start_date asc", nativeQuery = true)
    List<TimeBlock> findByTeamAndAccessiblePositionAndDate(final long teamId, final String position, final String date);

    List<TimeBlock> findAllByTeamId(final long teamId);
}
