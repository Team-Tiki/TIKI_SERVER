package com.tiki.server.timeblock.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tiki.server.common.entity.Position;
import com.tiki.server.team.entity.Team;
import com.tiki.server.timeblock.entity.TimeBlock;

public interface TimeBlockRepository extends CrudRepository<TimeBlock, Long> {
	List<TimeBlock> findByTeamAndAccessiblePosition(Team team, Position position);
}
