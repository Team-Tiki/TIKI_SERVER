package com.tiki.server.team.service;

import static com.tiki.server.common.entity.Position.ADMIN;

import java.util.List;

import com.tiki.server.document.adapter.DocumentDeleter;
import com.tiki.server.document.adapter.DocumentFinder;
import com.tiki.server.document.entity.Document;
import com.tiki.server.external.util.AwsHandler;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerDeleter;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import com.tiki.server.team.adapter.TeamDeleter;
import com.tiki.server.team.adapter.TeamFinder;
import com.tiki.server.team.dto.request.TeamInformUpdateServiceRequest;
import com.tiki.server.team.dto.response.CategoriesGetResponse;
import com.tiki.server.team.dto.response.TeamsGetResponse;

import com.tiki.server.team.dto.response.UsageGetResponse;
import com.tiki.server.team.service.dto.response.TeamInformGetResponse;
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
    private final AwsHandler awsHandler;

    @Transactional
    public TeamCreateResponse createTeam(final long memberId, final TeamCreateRequest request) {
        Member member = memberFinder.findById(memberId);
        Team team = teamSaver.save(createTeam(request, member.getUniv()));
        memberTeamManagerSaver.save(createMemberTeamManager(member, team, ADMIN));
        return TeamCreateResponse.from(team);
    }

    public TeamsGetResponse getAllTeams(final long memberId) {
        Member member = memberFinder.findById(memberId);
        University univ = member.getUniv();
        List<Team> team = teamFinder.findAllByUniv(univ);
        return TeamsGetResponse.from(team);
    }

    public CategoriesGetResponse getCategories() {
        Category[] categories = Category.values();
        return CategoriesGetResponse.from(categories);
    }

    @Transactional
    public void deleteTeam(final long memberId, final long teamId) {
        checkIsAdmin(memberId, teamId);
        List<MemberTeamManager> memberTeamManagers = memberTeamManagerFinder.findAllByTeamId(teamId);
        memberTeamManagerDeleter.deleteAll(memberTeamManagers);
        List<Document> documents = documentFinder.findAllByTeamId(teamId);
        documentDeleter.deleteAll(documents);
        timeBlockDeleter.deleteAllByTeamId(teamId);
        teamDeleter.deleteById(teamId);
    }

    public TeamInformGetResponse getTeamInform(final long teamId) {
        return TeamInformGetResponse.from(teamFinder.findById(teamId));
    }

    private Team createTeam(final TeamCreateRequest request, final University univ) {
        return Team.of(request, univ);
    }

    @Transactional
    public void updateTeamInform(final TeamInformUpdateServiceRequest request) {
        checkIsAdmin(request.memberId(), request.teamId());
        Team team = teamFinder.findById(request.teamId());
        team.updateInform(request.teamName(), request.teamIconUrl());
        updateIconUrlS3(team, request.teamIconUrl());
    }

    @Transactional
    public void alterAdmin(final long memberId, final long teamId, final long targetId) {
        MemberTeamManager oldAdmin = checkIsAdmin(memberId, teamId);
        MemberTeamManager newAdmin = memberTeamManagerFinder.findByMemberIdAndTeamId(targetId, teamId);
        oldAdmin.updatePositionToExecutive();
        newAdmin.updatePositionToAdmin();
    }

    public UsageGetResponse getCapacityInfo(final long memberId, final long teamId) {
        memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
        Team team = teamFinder.findById(teamId);
        long capacity = team.getCapacity();
        long usage = team.getUsage();
        return UsageGetResponse.of(capacity, usage);
    }

    private MemberTeamManager createMemberTeamManager(final Member member, final Team team, final Position position) {
        return MemberTeamManager.of(member, team, position);
    }

    private void updateIconUrlS3(final Team team, final String iconUrl) {
        if (!team.isDefaultImage() && !team.isSameIconUrl(iconUrl)) {
            awsHandler.deleteFile(team.getIconImageUrl());
        }
    }

    private MemberTeamManager checkIsAdmin(final long memberId, final long teamId) {
        MemberTeamManager accessMember = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
        accessMember.checkMemberAccessible(ADMIN);
        return accessMember;
    }
}
