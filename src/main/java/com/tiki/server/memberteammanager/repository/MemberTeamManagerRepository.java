package com.tiki.server.memberteammanager.repository;

import com.tiki.server.email.Email;
import com.tiki.server.memberteammanager.repository.projection.TeamMemberInformGetProjection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiki.server.memberteammanager.entity.MemberTeamManager;
import org.springframework.data.jpa.repository.Query;

public interface MemberTeamManagerRepository extends JpaRepository<MemberTeamManager, Long> {

    Optional<MemberTeamManager> findByMemberIdAndTeamId(final long memberId, final long teamId);

    List<MemberTeamManager> findAllByTeamId(final long teamId);

    List<MemberTeamManager> findAllByMemberIdOrderByCreatedAt(final long memberId);

    @Query("SELECT m.name AS memberName, m.email AS memberEmail, mtm.position AS memberPosition " +
            "FROM MemberTeamManager mtm " +
            "JOIN Member m ON mtm.memberId = m.id " +
            "WHERE mtm.teamId = :teamId")
    List<TeamMemberInformGetProjection> findTeamMembersByTeamId(final long teamId);

    @Query("SELECT CASE WHEN COUNT(mtm) > 0 THEN true ELSE false END " +
            "FROM MemberTeamManager mtm " +
            "JOIN Member m ON mtm.memberId = m.id " +
            "WHERE mtm.teamId = :teamId AND m.email = :email")
    boolean existsByTeamIdAndMemberEmail(final long teamId, final Email email);
}