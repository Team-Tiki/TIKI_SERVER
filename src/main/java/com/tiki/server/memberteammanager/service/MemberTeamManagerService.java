package com.tiki.server.memberteammanager.service;

import com.tiki.server.memberteammanager.adapter.MemberTeamManagerDeleter;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerSaver;
import com.tiki.server.memberteammanager.entity.MemberTeamManager;
import com.tiki.server.memberteammanager.repository.projection.TeamMemberInformGetProjection;
import com.tiki.server.memberteammanager.service.dto.response.TeamMembersGetResponse;
import com.tiki.server.note.adapter.NoteFinder;
import com.tiki.server.note.entity.Note;
import com.tiki.server.team.exception.TeamException;
import com.tiki.server.memberteammanager.service.dto.response.MemberTeamInformGetResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.tiki.server.common.entity.Position.ADMIN;
import static com.tiki.server.team.message.ErrorCode.INVALID_AUTHORIZATION_DELETE;
import static com.tiki.server.team.message.ErrorCode.TOO_HIGH_AUTHORIZATION;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberTeamManagerService {

    private final NoteFinder noteFinder;
    private final MemberTeamManagerFinder memberTeamManagerFinder;
    private final MemberTeamManagerDeleter memberTeamManagerDeleter;

    @Transactional
    public void kickOutMemberFromTeam(final long memberId, final long teamId, final long kickOutMemberId) {
        MemberTeamManager accessMember = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
        checkIsAdmin(accessMember);
        MemberTeamManager kickOutMember = memberTeamManagerFinder.findByMemberIdAndTeamId(kickOutMemberId, teamId);
        deleteNoteDependency(kickOutMemberId, teamId);
        memberTeamManagerDeleter.delete(kickOutMember);
    }

    @Transactional
    public void leaveTeam(final long memberId, final long teamId) {
        MemberTeamManager accessMember = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
        checkIsNotAdmin(accessMember);
        deleteNoteDependency(memberId, teamId);
        memberTeamManagerDeleter.delete(accessMember);
    }

    public MemberTeamInformGetResponse getMemberTeamInform(final long memberId, final long teamId) {
        MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
        return MemberTeamInformGetResponse.of(memberTeamManager.getPosition(), memberTeamManager.getName());
    }

    @Transactional
    public void updateTeamMemberName(final long memberId, final long teamId, final String name) {
        MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
        memberTeamManager.updateName(name);
    }

    public TeamMembersGetResponse getMembers(final long teamId, final long memberId) {
        MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
        List<TeamMemberInformGetProjection> teamMembers = memberTeamManagerFinder.findNameAndEmailByMemberIdAndTeamId(teamId);
        return TeamMembersGetResponse.from(teamMembers);
    }

    private void checkIsAdmin(MemberTeamManager memberTeamManager) {
        if (!memberTeamManager.getPosition().equals(ADMIN)) {
            throw new TeamException(INVALID_AUTHORIZATION_DELETE);
        }
    }

    private void checkIsNotAdmin(final MemberTeamManager memberTeamManager) {
        if (memberTeamManager.getPosition().equals(ADMIN)) {
            throw new TeamException(TOO_HIGH_AUTHORIZATION);
        }
    }

    private void deleteNoteDependency(final long memberId, final long teamId) {
        List<Note> notes = noteFinder.findAllByMemberIdAndTeamId(memberId, teamId);
        notes.forEach(Note::deleteMemberDependency);
    }
}
