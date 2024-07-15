package com.tiki.server.memberteammanager.repository;

import java.util.Optional;

import com.tiki.server.memberteammanager.dto.response.BelongTeamsResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tiki.server.memberteammanager.entity.MemberTeamManager;
import org.springframework.data.jpa.repository.Query;

public interface MemberTeamManagerRepository extends JpaRepository<MemberTeamManager, Long> {
	Optional<MemberTeamManager> findByMemberIdAndTeamId(Long memberId, Long teamId);

	@Query("SELECT t" +
			"FROM team t" +
			"JOIN memberTeamManager.team mm where mm.id =memberId")
	Optional<BelongTeamsResponse> findBelongTeamByMemberId(long memberId);
}

//	@Query("select d from Document d join d.timeBlock t where t.team.id = :teamId")
//	List<Document> findAllByTeamId(@Param("teamId") long teamId);
//
//	@Query("select d from Document d join fetch d.timeBlock t where t.team.id = :teamId")
//	List<Document> fineAllByTeamId(long teamId);