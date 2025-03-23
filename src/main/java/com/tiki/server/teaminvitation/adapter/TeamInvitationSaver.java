package com.tiki.server.teaminvitation.adapter;


import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.teaminvitation.entity.Invitation;
import com.tiki.server.teaminvitation.repository.InvitationRepository;
import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class TeamInvitationSaver {

    private final InvitationRepository invitationRepository;

    public Invitation createTeamInvitation(final Invitation invitation){
        return invitationRepository.save(invitation);
    }
}
