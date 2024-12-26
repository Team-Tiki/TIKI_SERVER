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
        //초대 메일 보내기

        teamInvitationSaver.createTeamInvitation(TeamInvitation.of(request.teamId(), request.email()));

    }
}
