package com.tiki.server.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiki.server.team.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
