package com.tiki.server.timeblock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tiki.server.timeblock.entity.TimeBlock;

public interface TimeBlockRepository extends JpaRepository<TimeBlock, Long> {

	@Query(value = "select * from time_block "
		+ "where team_id = :teamId and accessible_position = :position and to_char(created_at, 'YYYY-MM') = :date "
		+ "order by start_date DESC", nativeQuery = true)
	List<TimeBlock> findByTeamAndAccessiblePositionAndDate(long teamId, String position, String date);
}
