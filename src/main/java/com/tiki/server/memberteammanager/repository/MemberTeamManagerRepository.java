package com.tiki.server.memberteammanager.repository;

import com.tiki.server.memberteammanager.repository.projection.TeamMemberInformGetProjection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiki.server.memberteammanager.entity.MemberTeamManager;
import org.springframework.data.jpa.repository.Query;

public interface MemberTeamManagerRepository extends JpaRepository<MemberTeamManager, Long> {

    Optional<MemberTeamManager> findByMemberIdAndTeamId(final long memberId, final long teamId);

    List<MemberTeamManager> findAllByTeamId(final long teamId);

    @Query("select m from MemberTeamManager m join fetch m.team t where m.member.id = :memberId")
    List<MemberTeamManager> findAllBelongTeamByMemberId(long memberId);

    @Query("SELECT m.name AS memberName, m.email AS memberEmail, mtm.position AS memberPosition " +
            "FROM MemberTeamManager mtm " +
            "JOIN mtm.member m " +
            "WHERE mtm.team.id = :teamId")
    List<TeamMemberInformGetProjection> findTeamMembersByTeamId(final long teamId);
}