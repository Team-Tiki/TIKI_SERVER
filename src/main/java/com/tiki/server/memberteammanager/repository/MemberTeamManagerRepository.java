package com.tiki.server.memberteammanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiki.server.memberteammanager.entity.MemberTeamManager;
import org.springframework.data.jpa.repository.Query;

public interface MemberTeamManagerRepository extends JpaRepository<MemberTeamManager, Long> {

    Optional<MemberTeamManager> findByMemberIdAndTeamId(final long memberId, final long teamId);

    List<MemberTeamManager> findAllByTeamId(final long teamId);

    @Query("select m from MemberTeamManager m join fetch m.team t where m.member.id = :memberId")
    List<MemberTeamManager> findAllBelongTeamByMemberId(final long memberId);
}