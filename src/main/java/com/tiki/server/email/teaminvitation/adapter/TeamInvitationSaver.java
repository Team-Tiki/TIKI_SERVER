package com.tiki.server.email.teaminvitation.adapter;


import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.email.teaminvitation.entity.TeamInvitation;
import com.tiki.server.email.teaminvitation.repository.TeamInvitationRepository;
import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class TeamInvitationSaver {

    private final TeamInvitationRepository teamInvitationRepository;

    public TeamInvitation createTeamInvitation(final TeamInvitation teamInvitation){
        return teamInvitationRepository.save(teamInvitation);
    }
}
