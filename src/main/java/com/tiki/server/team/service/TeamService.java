package com.tiki.server.team.service;

import static com.tiki.server.common.entity.Position.ADMIN;

import com.tiki.server.memberteammanager.dto.response.BelongTeamsResponse;
import com.tiki.server.team.adapter.TeamFinder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiki.server.common.entity.Position;
import com.tiki.server.common.entity.University;
import com.tiki.server.member.adapter.MemberFinder;
import com.tiki.server.member.entity.Member;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerSaver;
import com.tiki.server.memberteammanager.entity.MemberTeamManager;
import com.tiki.server.team.adapter.TeamSaver;
import com.tiki.server.team.dto.request.TeamCreateRequest;
import com.tiki.server.team.dto.response.TeamCreateResponse;
import com.tiki.server.team.entity.Team;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeamService {

	private final TeamSaver teamSaver;
	private final TeamFinder teamFinder;
	private final MemberFinder memberFinder;
	private final MemberTeamManagerSaver memberTeamManagerSaver;

	@Transactional
	public TeamCreateResponse createTeam(long memberId, TeamCreateRequest request) {
		val member = memberFinder.findById(memberId);
		val team = teamSaver.save(createTeam(request, member.getUniv()));
		memberTeamManagerSaver.save(createMemberTeamManager(member, team, ADMIN));
		return TeamCreateResponse.from(team);
	}

	private Team createTeam(TeamCreateRequest request, University univ) {
		return Team.of(request, univ);
	}

	private MemberTeamManager createMemberTeamManager(Member member, Team team, Position position) {
		return MemberTeamManager.of(member, team, position);
	}

	public BelongTeamsResponse findBelongTeams(long memberId){
		val belongTeams = teamFinder.findBelongTeamByMemberId(memberId);

	}
}
