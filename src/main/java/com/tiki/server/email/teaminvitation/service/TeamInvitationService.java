package com.tiki.server.email.teaminvitation.service;

import com.tiki.server.common.entity.Position;
import com.tiki.server.email.exception.TeamInvitationException;
import com.tiki.server.email.teaminvitation.adapter.TeamInvitationDeleter;
import com.tiki.server.email.teaminvitation.adapter.TeamInvitationFinder;
import com.tiki.server.email.teaminvitation.entity.TeamInvitation;
import com.tiki.server.email.teaminvitation.service.dto.TeamInvitationEmailsGetResponse;
import com.tiki.server.email.teaminvitation.service.dto.TeamInvitationInformGetResponse;
import com.tiki.server.member.adapter.MemberFinder;
import com.tiki.server.member.entity.Member;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerSaver;
import com.tiki.server.memberteammanager.entity.MemberTeamManager;
import com.tiki.server.team.adapter.TeamFinder;
import com.tiki.server.team.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.tiki.server.email.teaminvitation.messages.ErrorCode.ALREADY_INVITED_MEMBER;
import static com.tiki.server.email.teaminvitation.messages.ErrorCode.NOT_MATCHED_MEMBER_INFORM;

@Service
@RequiredArgsConstructor
public class TeamInvitationService {

    private final TeamInvitationDeleter teamInvitationDeleter;
    private final TeamInvitationFinder teamInvitationFinder;
    private final MemberTeamManagerFinder memberTeamManagerFinder;
    private final MemberTeamManagerSaver memberTeamManagerSaver;
    private final TeamFinder teamFinder;
    private final MemberFinder memberFinder;

    public TeamInvitationInformGetResponse getInvitationInform(final long invitationId) {
        TeamInvitation invitation = teamInvitationFinder.findByInvitationId(invitationId);
        Team team = teamFinder.findById(invitation.getTeamId());
        return TeamInvitationInformGetResponse.of(invitation, team);
    }

    public void createTeamMemberFromInvitation(final long memberId, final long teamId, final long invitationId) {
        checkIsPresentTeamMember(memberId, teamId);
        Member member = memberFinder.findById(memberId);
        Team team = teamFinder.findById(teamId);
        TeamInvitation invitation = teamInvitationFinder.findByInvitationId(invitationId);
        checkMemberMatched(invitation, member);
        memberTeamManagerSaver.save(MemberTeamManager.of(member, team, Position.EXECUTIVE));
    }

    public void deleteTeamInvitationFromAdmin(final long memberId, final long teamId, final long invitationId) {
        MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
        memberTeamManager.checkMemberAccessible(Position.ADMIN);
        TeamInvitation teamInvitation = teamInvitationFinder.findByInvitationId(invitationId);
        teamInvitationDeleter.deleteTeamInvitation(teamInvitation);
    }

    public void deleteTeamInvitation(final long memberId, final long invitationId) {
        TeamInvitation invitation = teamInvitationFinder.findByInvitationId(invitationId);
        Member member = memberFinder.findById(memberId);
        checkMemberMatched(invitation, member);
        teamInvitationDeleter.deleteTeamInvitation(invitation);
    }


    public TeamInvitationEmailsGetResponse getInvitations(final long memberId, final long teamId) {
        MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
        memberTeamManager.checkMemberAccessible(Position.ADMIN);
        List<TeamInvitation> teamInvitations = teamInvitationFinder.findAllByTeamId(teamId);
        return TeamInvitationEmailsGetResponse.from(teamInvitations);
    }

    private static void checkMemberMatched(TeamInvitation teamInvitation, Member member) {
        if (!teamInvitation.getEmail().equals(member.getEmail())) {
            throw new TeamInvitationException(NOT_MATCHED_MEMBER_INFORM);
        }
    }

    private void checkIsPresentTeamMember(long memberId, long teamId) {
        if (memberTeamManagerFinder.checkIsPresent(memberId, teamId)) {
            throw new TeamInvitationException(ALREADY_INVITED_MEMBER);
        }
    }
}
