package com.tiki.server.email.teaminvitation.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.email.teaminvitation.entity.TeamInvitation;
import com.tiki.server.email.teaminvitation.repository.TeamInvitationRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RepositoryAdapter
@RequiredArgsConstructor
public class TeamInvitationDeleter {

    private final TeamInvitationRepository teamInvitationRepository;

    public void deleteTeamInvitation(final TeamInvitation teamInvitation) {
        teamInvitationRepository.delete(teamInvitation);
    }

    public void deleteAll(final List<TeamInvitation> expiredInvitation) {
        teamInvitationRepository.deleteAll(expiredInvitation);
    }
}
