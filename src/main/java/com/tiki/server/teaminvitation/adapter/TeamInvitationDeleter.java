package com.tiki.server.teaminvitation.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.teaminvitation.entity.TeamInvitation;
import com.tiki.server.teaminvitation.repository.TeamInvitationRepository;
import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class TeamInvitationDeleter {

    private final TeamInvitationRepository teamInvitationRepository;

    public void deleteTeamInvitation(final TeamInvitation teamInvitation) {
        teamInvitationRepository.delete(teamInvitation);
    }
}
