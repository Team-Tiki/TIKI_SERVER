package com.tiki.server.teaminvitation.adapter;


import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.teaminvitation.entity.TeamInvitation;
import com.tiki.server.teaminvitation.repository.TeamInvitationRepository;
import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class TeamInvitationSaver {

    private final TeamInvitationRepository teamInvitationRepository;

    public TeamInvitation createTeamInvitation(final TeamInvitation teamInvitation){
        return teamInvitationRepository.save(teamInvitation);
    }
}
