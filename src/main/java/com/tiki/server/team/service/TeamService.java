package com.tiki.server.team.service;

import static com.tiki.server.common.entity.Position.ADMIN;
import static com.tiki.server.team.message.ErrorCode.INVALID_AUTHORIZATION_DELETE;

import java.util.HashSet;

import com.tiki.server.document.adapter.DocumentDeleter;
import com.tiki.server.document.adapter.DocumentFinder;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerDeleter;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import com.tiki.server.team.adapter.TeamDeleter;
import com.tiki.server.team.adapter.TeamFinder;
import com.tiki.server.team.dto.response.CategoriesGetResponse;
import com.tiki.server.team.dto.response.TeamsGetResponse;

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
import com.tiki.server.team.entity.Category;
import com.tiki.server.team.entity.Team;
import com.tiki.server.team.exception.TeamException;
import com.tiki.server.timeblock.adapter.TimeBlockDeleter;
import com.tiki.server.timeblock.entity.TimeBlock;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeamService {

	private final TeamSaver teamSaver;
	private final TeamFinder teamFinder;
	private final TeamDeleter teamDeleter;
	private final MemberFinder memberFinder;
	private final DocumentFinder documentFinder;
	private final DocumentDeleter documentDeleter;
	private final TimeBlockDeleter timeBlockDeleter;
	private final MemberTeamManagerFinder memberTeamManagerFinder;
	private final MemberTeamManagerDeleter memberTeamManagerDeleter;
	private final MemberTeamManagerSaver memberTeamManagerSaver;

	@Transactional
	public TeamCreateResponse createTeam(long memberId, TeamCreateRequest request) {
		val member = memberFinder.findById(memberId);
		val team = teamSaver.save(createTeam(request, member.getUniv()));
		memberTeamManagerSaver.save(createMemberTeamManager(member, team, ADMIN));
		return TeamCreateResponse.from(team);
	}

	public TeamsGetResponse getAllTeam(long memberId) {
		val member = memberFinder.findById(memberId);
		val univ = member.getUniv();
		val team = teamFinder.findAllByUniv(univ);
		return TeamsGetResponse.from(team);
	}

	public CategoriesGetResponse getCategories() {
		val categories = Category.values();
		return CategoriesGetResponse.from(categories);
	}

	@Transactional
	public void deleteTeam(long memberId, long teamId) {
		checkIsAdmin(memberId, teamId);
		val memberTeamManagers = memberTeamManagerFinder.findAllByTeamId(teamId);
		memberTeamManagerDeleter.deleteAll(memberTeamManagers);
		val documents = documentFinder.findAllByTeamId(teamId);
		val timeBlocks = new HashSet<TimeBlock>();
		documents.forEach(document -> timeBlocks.add(document.getTimeBlock()));
		documentDeleter.deleteAll(documents);
		timeBlockDeleter.deleteAll(timeBlocks);
		teamDeleter.deleteById(teamId);
	}

	private Team createTeam(TeamCreateRequest request, University univ) {
		return Team.of(request, univ);
	}

	private MemberTeamManager createMemberTeamManager(Member member, Team team, Position position) {
		return MemberTeamManager.of(member, team, position);
	}

	private void checkIsAdmin(long memberId, long teamId) {
		val memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
		if (!memberTeamManager.getPosition().equals(ADMIN)) {
			throw new TeamException(INVALID_AUTHORIZATION_DELETE);
		}
		memberTeamManagerDeleter.delete(memberTeamManager);
	}
}
