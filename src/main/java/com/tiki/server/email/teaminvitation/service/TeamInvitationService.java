package com.tiki.server.email.teaminvitation.service;

import com.tiki.server.common.entity.Position;
import com.tiki.server.email.emailsender.entity.MailSender;
import com.tiki.server.email.teaminvitation.adapter.TeamInvitationSaver;
import com.tiki.server.email.teaminvitation.entity.TeamInvitation;
import com.tiki.server.email.teaminvitation.service.dto.TeamInvitationCreateServiceRequest;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import com.tiki.server.memberteammanager.entity.MemberTeamManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamInvitationService {

    private final TeamInvitationSaver teamInvitationSaver;
    private final MemberTeamManagerFinder memberTeamManagerFinder;
    private final MailSender mailSender;

    public void createTeamInvitation(TeamInvitationCreateServiceRequest request) {
        MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(request.memberId(),
                request.teamId());
        memberTeamManager.checkMemberAccessible(Position.EXECUTIVE);
        mailSender.sendTeamInvitationMail(request.email().getEmail(), request.teamId());
        teamInvitationSaver.createTeamInvitation(TeamInvitation.of(request.teamId(), request.email()));
    }
}
