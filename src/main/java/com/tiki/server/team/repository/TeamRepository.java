package com.tiki.server.team.repository;

import com.tiki.server.memberteammanager.dto.response.BelongTeamsResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tiki.server.team.entity.Team;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("SELECT t FROM Team t JOIN MemberTeamManager.team mm where mm.id = :memberId")
    Optional<Team> findBelongTeamByMemberId(long memberId);

}
