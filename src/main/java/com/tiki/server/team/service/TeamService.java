package com.tiki.server.team.service;

import static com.tiki.server.common.entity.Position.ADMIN;
import static com.tiki.server.team.message.ErrorCode.EXCEED_TEAM_NUMBER;

import com.tiki.server.team.exception.TeamException;
import java.util.List;

import com.tiki.server.document.adapter.DeletedDocumentAdapter;
import com.tiki.server.document.adapter.DocumentDeleter;
import com.tiki.server.document.adapter.DocumentFinder;
import com.tiki.server.document.entity.DeletedDocument;
import com.tiki.server.document.entity.Document;
import com.tiki.server.documenttimeblockmanager.adapter.DTBAdapter;
import com.tiki.server.external.util.AwsHandler;
import com.tiki.server.folder.adapter.FolderDeleter;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerDeleter;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import com.tiki.server.note.adapter.NoteDeleter;
import com.tiki.server.notedocumentmanager.adapter.NDDeleter;
import com.tiki.server.notetimeblockmanager.adapter.NTBDeleter;
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
import com.tiki.server.timeblock.adapter.TimeBlockFinder;
import com.tiki.server.timeblock.entity.TimeBlock;

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
    private final TimeBlockFinder timeBlockFinder;
    private final TimeBlockDeleter timeBlockDeleter;
    private final MemberTeamManagerFinder memberTeamManagerFinder;
    private final MemberTeamManagerDeleter memberTeamManagerDeleter;
    private final MemberTeamManagerSaver memberTeamManagerSaver;
    private final DTBAdapter dtbAdapter;
    private final NTBDeleter ntbDeleter;
    private final NDDeleter ndDeleter;
    private final DeletedDocumentAdapter deletedDocumentAdapter;
    private final FolderDeleter folderDeleter;
    private final NoteDeleter noteDeleter;
    private final AwsHandler awsHandler;

    @Transactional
    public TeamCreateResponse createTeam(final long memberId, final TeamCreateRequest request) {
        Member member = memberFinder.findById(memberId);
        checkTeamNumber(memberId);
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
        deleteDocuments(teamId);
        deleteTimeBlocks(teamId);
        noteDeleter.deleteAllByTeamId(teamId);
        folderDeleter.deleteAllByTeamId(teamId);
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

    private void deleteDocuments(final long teamId) {
        List<Document> documents = documentFinder.findAllByTeamId(teamId);
        documents.forEach(Document -> awsHandler.deleteFile(Document.getFileKey()));
        List<DeletedDocument> deletedDocuments = deletedDocumentAdapter.get(teamId);
        deletedDocuments.forEach(DeletedDocument -> awsHandler.deleteFile(DeletedDocument.getFileKey()));
        deletedDocumentAdapter.deleteAllByTeamId(teamId);
        ndDeleter.deleteAllByDocuments(documents.stream().map(Document::getId).toList());
        documentDeleter.deleteAll(documents);
    }

    private void deleteTimeBlocks(final long teamId) {
        List<TimeBlock> timeBlocks = timeBlockFinder.findAllByTeamId(teamId);
        timeBlocks.forEach(ntbDeleter::deleteAllByTimeBlock);
        timeBlocks.forEach(dtbAdapter::deleteAllByTimeBlock);
        timeBlockDeleter.deleteAllByTeamId(teamId);
    }

    private void checkTeamNumber(final long memberId) {
        List<MemberTeamManager> joinedTeams = memberTeamManagerFinder.findAllByMemberIdOrderByCreatedAt(
                memberId);
        if (joinedTeams.size() > 8) {
            throw new TeamException(EXCEED_TEAM_NUMBER);
        }
    }
}

