package com.tiki.server.team.repository;

import com.tiki.server.common.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tiki.server.team.entity.Team;
import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findAllByUniv(University university);
}
