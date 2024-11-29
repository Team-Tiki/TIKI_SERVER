package com.tiki.server.team.service;

import static com.tiki.server.common.entity.Position.ADMIN;
import static com.tiki.server.team.message.ErrorCode.INVALID_AUTHORIZATION_DELETE;
import static com.tiki.server.team.message.ErrorCode.TOO_HIGH_AUTHORIZATION;

import java.util.List;
import java.util.Optional;

import com.tiki.server.document.adapter.DocumentDeleter;
import com.tiki.server.document.adapter.DocumentFinder;
import com.tiki.server.document.entity.Document;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerDeleter;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import com.tiki.server.note.adapter.NoteFinder;
import com.tiki.server.note.entity.Note;
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
    private final NoteFinder noteFinder;
    private final DocumentFinder documentFinder;
    private final DocumentDeleter documentDeleter;
    private final TimeBlockDeleter timeBlockDeleter;
    private final MemberTeamManagerFinder memberTeamManagerFinder;
    private final MemberTeamManagerDeleter memberTeamManagerDeleter;
    private final MemberTeamManagerSaver memberTeamManagerSaver;

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
        List<TeamVO> team = teamFinder.findAllByUniv(univ);
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

    @Transactional
    public void kickOutMemberFromTeam(final long memberId, final long teamId, final long kickOutMemberId) {
        checkIsAdmin(memberId, teamId);
        Optional<MemberTeamManager> memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(kickOutMemberId, teamId);
        deleteNoteDependency(memberId, teamId);
        memberTeamManager.ifPresent(memberTeamManagerDeleter::delete);
    }

    @Transactional
    public void leaveTeam(final long memberId, final long teamId) {
        MemberTeamManager memberTeamManager = checkIsNotAdmin(memberId, teamId);
        deleteNoteDependency(memberId, teamId);
        memberTeamManagerDeleter.delete(memberTeamManager);
    }

    private void deleteNoteDependency(final long memberId, final long teamId) {
        List<Note> notes = noteFinder.findAllByMemberIdAndTeamId(memberId, teamId);
        notes.forEach(Note::deleteMemberDependency);
    }

    private Team createTeam(final TeamCreateRequest request, final University univ) {
        return Team.of(request, univ);
    }

    private MemberTeamManager createMemberTeamManager(final Member member, final Team team, final Position position) {
        return MemberTeamManager.of(member, team, position);
    }

    private void checkIsAdmin(final long memberId, final long teamId) {
        MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamIdOrElseThrow(memberId, teamId);
        if (!memberTeamManager.getPosition().equals(ADMIN)) {
            throw new TeamException(INVALID_AUTHORIZATION_DELETE);
        }
    }

    private MemberTeamManager checkIsNotAdmin(final long memberId, final long teamId) {
        MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamIdOrElseThrow(memberId, teamId);
        if (memberTeamManager.getPosition().equals(ADMIN)) {
            throw new TeamException(TOO_HIGH_AUTHORIZATION);
        }
        return memberTeamManager;
    }
}
