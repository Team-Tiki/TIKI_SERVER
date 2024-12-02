package com.tiki.server.memberteammanager.repository;

import com.tiki.server.memberteammanager.repository.projection.NameAndEmailProjection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiki.server.memberteammanager.entity.MemberTeamManager;
import org.springframework.data.jpa.repository.Query;

public interface MemberTeamManagerRepository extends JpaRepository<MemberTeamManager, Long> {

    Optional<MemberTeamManager> findByMemberIdAndTeamId(Long memberId, Long teamId);

    List<MemberTeamManager> findAllByTeamId(Long teamId);

    @Query("select m from MemberTeamManager m join fetch m.team t where m.member.id = :memberId")
    List<MemberTeamManager> findAllBelongTeamByMemberId(long memberId);

    @Query("SELECT m.name AS name, m.email AS email " +
            "FROM MemberTeamManager mtm JOIN mtm.member m " +
            "WHERE mtm.member.id = :memberId AND mtm.team.id = :teamId")
    List<NameAndEmailProjection> findNameAndEmailByMemberIdAndTeamId(final long memberId, final long teamId);

}