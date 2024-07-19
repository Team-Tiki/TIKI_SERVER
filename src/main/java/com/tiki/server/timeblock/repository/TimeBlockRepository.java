package com.tiki.server.timeblock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tiki.server.timeblock.entity.TimeBlock;

public interface TimeBlockRepository extends JpaRepository<TimeBlock, Long> {

	@Query(value = "select * from time_block "
		+ "where team_id = :teamId and accessible_position = :position and "
		+ "(to_char(start_date, 'YYYY-MM') <= :date and to_char(end_date, 'YYYY-MM') >= :date) "
		+ "order by start_date asc", nativeQuery = true)
	List<TimeBlock> findByTeamAndAccessiblePositionAndDate(long teamId, String position, String date);

	@Query("select t from TimeBlock t join fetch d.timeBlock where d.id = :documentId")
	List<TimeBlock> findByTeamId(long teamId);
}
