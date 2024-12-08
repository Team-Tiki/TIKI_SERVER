package com.tiki.server.memberteammanager.service;

import com.tiki.server.memberteammanager.adapter.MemberTeamManagerDeleter;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerSaver;
import com.tiki.server.memberteammanager.entity.MemberTeamManager;
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
    private final MemberTeamManagerSaver memberTeamManagerSaver;

    @Transactional
    public void kickOutMemberFromTeam(final long memberId, final long teamId, final long kickOutMemberId) {
        checkIsAdmin(memberId, teamId);
        MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(kickOutMemberId, teamId);
        deleteNoteDependency(kickOutMemberId, teamId);
        memberTeamManagerDeleter.delete(memberTeamManager);
    }

    @Transactional
    public void leaveTeam(final long memberId, final long teamId) {
        MemberTeamManager memberTeamManager = checkIsNotAdmin(memberId, teamId);
        deleteNoteDependency(memberId, teamId);
        memberTeamManagerDeleter.delete(memberTeamManager);
    }

    public MemberTeamInformGetResponse getMemberTeamInform(final long memberId, final long teamId) {
        MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
        return MemberTeamInformGetResponse.from(memberTeamManager.getPosition(), memberTeamManager.getName());
    }

    @Transactional
    public void updateTeamMemberName(final long memberId, final long teamId, final String name) {
        MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
        memberTeamManager.updateName(name);
    }

    private void checkIsAdmin(final long memberId, final long teamId) {
        MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
        if (!memberTeamManager.getPosition().equals(ADMIN)) {
            throw new TeamException(INVALID_AUTHORIZATION_DELETE);
        }
    }

    private MemberTeamManager checkIsNotAdmin(final long memberId, final long teamId) {
        MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
        if (memberTeamManager.getPosition().equals(ADMIN)) {
            throw new TeamException(TOO_HIGH_AUTHORIZATION);
        }
        return memberTeamManager;
    }

    private void deleteNoteDependency(final long memberId, final long teamId) {
        List<Note> notes = noteFinder.findAllByMemberIdAndTeamId(memberId, teamId);
        notes.forEach(Note::deleteMemberDependency);
    }
}
