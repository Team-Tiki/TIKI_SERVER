package com.tiki.server.team.repository;

import com.tiki.server.common.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tiki.server.team.entity.Team;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Optional<List<Team>> findAllByUniv(University university);
}
