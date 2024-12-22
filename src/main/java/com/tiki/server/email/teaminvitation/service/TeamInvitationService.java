package com.tiki.server.email.teaminvitation.service;

import com.tiki.server.common.entity.Position;
import com.tiki.server.email.teaminvitation.adapter.TeamInvitationSaver;
import com.tiki.server.email.teaminvitation.entity.TeamInvitation;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import com.tiki.server.memberteammanager.entity.MemberTeamManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamInvitationService {

    TeamInvitationSaver teamInvitationSaver;
    MemberTeamManagerFinder memberTeamManagerFinder;
    public void createTeamInvitation(long memberId, long teamId) {
        MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
        memberTeamManager.checkMemberAccessible(Position.EXECUTIVE);
        //초대 메일 보내기


    }
}
