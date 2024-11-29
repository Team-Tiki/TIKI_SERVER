package com.tiki.server.team.service;

import static com.tiki.server.common.entity.Position.ADMIN;
import static com.tiki.server.team.message.ErrorCode.INVALID_AUTHORIZATION_DELETE;

import java.util.List;

import com.tiki.server.document.adapter.DocumentDeleter;
import com.tiki.server.document.adapter.DocumentFinder;
import com.tiki.server.document.entity.Document;
import com.tiki.server.external.util.S3Handler;
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
import com.tiki.server.team.vo.TeamVO;
import com.tiki.server.timeblock.adapter.TimeBlockDeleter;

import lombok.RequiredArgsConstructor;

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
    private final S3Handler s3Handler;

    @Transactional
    public TeamCreateResponse createTeam(long memberId, TeamCreateRequest request) {
        Member member = memberFinder.findById(memberId);
        Team team = teamSaver.save(createTeam(request, member.getUniv()));
        memberTeamManagerSaver.save(createMemberTeamManager(member, team, ADMIN));
        return TeamCreateResponse.from(team);
    }

    public TeamsGetResponse getAllTeams(long memberId) {
        Member member = memberFinder.findById(memberId);
        University univ = member.getUniv();
        List<TeamVO> team = teamFinder.findAllByUniv(univ);
        return TeamsGetResponse.from(team);
    }

    public CategoriesGetResponse getCategories() {
        Category[] categories = Category.values();
        return CategoriesGetResponse.from(categories);
    }

    @Transactional
    public void deleteTeam(long memberId, long teamId) {
        checkIsAdmin(memberId, teamId);
        List<MemberTeamManager> memberTeamManagers = memberTeamManagerFinder.findAllByTeamId(teamId);
        memberTeamManagerDeleter.deleteAll(memberTeamManagers);
        List<Document> documents = documentFinder.findAllByTeamId(teamId);
        documentDeleter.deleteAll(documents);
        timeBlockDeleter.deleteAllByTeamId(teamId);
        teamDeleter.deleteById(teamId);
    }

    @Transactional
    public void updateTeamName(final long memberId, final long teamId, final String newTeamName) {
        checkIsAdmin(memberId, teamId);
        Team team = teamFinder.findById(teamId);
        team.setName(newTeamName);
    }

    @Transactional
    public void updateIconImage(final long memberId, final long teamId, final String iconImageUrl) {
        checkIsAdmin(memberId, teamId);
        Team team = teamFinder.findById(teamId);
        s3Handler.deleteFile(team.getIconImageUrl());
        team.setIconImageUrl(iconImageUrl);
    }

    @Transactional
    public void alterAdmin(final long memberId, final long teamId, final long targetId) {
        MemberTeamManager oldAdmin = checkIsAdmin(memberId, teamId);
        MemberTeamManager newAdmin = memberTeamManagerFinder.findByMemberIdAndTeamIdOrElseThrow(memberId, teamId);
        oldAdmin.setPositionToExecutive();
        newAdmin.setPositionToAdmin();
    }

    private Team createTeam(TeamCreateRequest request, University univ) {
        return Team.of(request, univ);
    }

    private MemberTeamManager createMemberTeamManager(Member member, Team team, Position position) {
        return MemberTeamManager.of(member, team, position);
    }

    private MemberTeamManager checkIsAdmin(long memberId, long teamId) {
        MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamIdOrElseThrow(memberId, teamId);
        if (!memberTeamManager.getPosition().equals(ADMIN)) {
            throw new TeamException(INVALID_AUTHORIZATION_DELETE);
        }
        return memberTeamManager;
    }
}
