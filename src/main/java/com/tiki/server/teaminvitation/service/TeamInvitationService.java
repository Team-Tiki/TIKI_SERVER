package com.tiki.server.teaminvitation.service;

import com.tiki.server.common.entity.Position;
import com.tiki.server.teaminvitation.entity.TeamInvitation;
import com.tiki.server.teaminvitation.exception.TeamInvitationException;
import com.tiki.server.teaminvitation.adapter.TeamInvitationDeleter;
import com.tiki.server.teaminvitation.adapter.TeamInvitationFinder;
import com.tiki.server.teaminvitation.service.dto.TeamInvitationEmailsGetResponse;
import com.tiki.server.teaminvitation.service.dto.TeamInvitationInformGetResponse;
import com.tiki.server.external.util.AwsHandler;
import com.tiki.server.member.adapter.MemberFinder;
import com.tiki.server.member.entity.Member;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerSaver;
import com.tiki.server.memberteammanager.entity.MemberTeamManager;
import com.tiki.server.team.adapter.TeamFinder;
import com.tiki.server.team.dto.response.TeamResponse;
import com.tiki.server.team.entity.Team;
import com.tiki.server.team.exception.TeamException;
import com.tiki.server.teaminvitation.messages.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import static com.tiki.server.team.message.ErrorCode.EXCEED_TEAM_NUMBER;

@Service
@RequiredArgsConstructor
public class TeamInvitationService {

    private final TeamInvitationDeleter teamInvitationDeleter;
    private final TeamInvitationFinder teamInvitationFinder;
    private final MemberTeamManagerFinder memberTeamManagerFinder;
    private final MemberTeamManagerSaver memberTeamManagerSaver;
    private final TeamFinder teamFinder;
    private final MemberFinder memberFinder;
    private final AwsHandler awsHandler;

    public TeamInvitationInformGetResponse getInvitationInform(final String invitationId) {
        TeamInvitation teamInvitation = teamInvitationFinder.findByInvitationId(invitationId);
        Team team = teamFinder.findById(teamInvitation.getTeamId());
        TeamResponse response = TeamResponse.createWithIcon(team, awsHandler.getDownloadPreSignedUrl(team.getIconImageKey()));
        return TeamInvitationInformGetResponse.of(teamInvitation, response);
    }

    @Transactional
    public void createTeamMemberFromInvitation(final long memberId, final long teamId, final String invitationId) {
        checkIsPresentTeamMember(memberId, teamId);
        Member member = memberFinder.findById(memberId);
        checkTeamNumber(memberId);
        Team team = teamFinder.findById(teamId);
        TeamInvitation teamInvitation = teamInvitationFinder.findByInvitationId(invitationId);
        checkMemberMatched(teamInvitation, member);
        memberTeamManagerSaver.save(MemberTeamManager.of(member, team, Position.EXECUTIVE));
        teamInvitationDeleter.deleteTeamInvitation(teamInvitation);
    }

    public void deleteTeamInvitationFromAdmin(final long memberId, final long teamId, final String invitationId) {
        MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
        memberTeamManager.checkMemberAccessible(Position.ADMIN);
        TeamInvitation teamInvitation = teamInvitationFinder.findByInvitationId(invitationId);
        teamInvitationDeleter.deleteTeamInvitation(teamInvitation);
    }

    public void deleteTeamInvitation(final long memberId, final String invitationId) {
        TeamInvitation teamInvitation = teamInvitationFinder.findByInvitationId(invitationId);
        Member member = memberFinder.findById(memberId);
        checkMemberMatched(teamInvitation, member);
        teamInvitationDeleter.deleteTeamInvitation(teamInvitation);
    }

    @Transactional(readOnly = true)
    public TeamInvitationEmailsGetResponse getInvitations(final long memberId, final long teamId) {
        MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
        memberTeamManager.checkMemberAccessible(Position.ADMIN);
        List<TeamInvitation> teamInvitations = teamInvitationFinder.findAllByIdStartingWith(String.valueOf(teamId));
        return TeamInvitationEmailsGetResponse.from(teamInvitations);
    }

    private void checkMemberMatched(TeamInvitation teamInvitation, Member member) {
        if (!teamInvitation.getEmail().equals(member.getEmail())) {
            throw new TeamInvitationException(ErrorCode.NOT_MATCHED_MEMBER_INFORM);
        }
    }

    private void checkIsPresentTeamMember(long memberId, long teamId) {
        if (memberTeamManagerFinder.checkIsPresent(memberId, teamId)) {
            throw new TeamInvitationException(ErrorCode.ALREADY_INVITED_MEMBER);
        }
    }

    private void checkTeamNumber(final long memberId) {
        List<MemberTeamManager> joinedTeams = memberTeamManagerFinder.findAllByMemberIdOrderByCreatedAt(
                memberId);
        if (joinedTeams.size() > 8) {
            throw new TeamException(EXCEED_TEAM_NUMBER);
        }
    }
}
