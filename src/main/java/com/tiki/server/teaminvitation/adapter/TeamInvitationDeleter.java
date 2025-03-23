package com.tiki.server.teaminvitation.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.teaminvitation.entity.Invitation;
import com.tiki.server.teaminvitation.repository.InvitationRepository;
import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class TeamInvitationDeleter {

    private final InvitationRepository invitationRepository;

    public void deleteTeamInvitation(final Invitation invitation) {
        invitationRepository.delete(invitation);
    }
}
