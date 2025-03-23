package com.tiki.server.teaminvitation.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.teaminvitation.entity.Invitation;
import com.tiki.server.teaminvitation.exception.TeamInvitationException;
import com.tiki.server.teaminvitation.repository.InvitationRepository;
import com.tiki.server.teaminvitation.messages.ErrorCode;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RepositoryAdapter
@RequiredArgsConstructor
public class TeamInvitationFinder {

    private final InvitationRepository invitationRepository;

    public Invitation findByInvitationId(final String invitationId) {
        return invitationRepository.findById(invitationId)
                .orElseThrow(() -> new TeamInvitationException(ErrorCode.INVALID_TEAM_INVITATION));
    }

    public List<Invitation> findAllByIdStartingWith(final String teamId) {
        return invitationRepository.findAllByIdStartingWith(teamId);
    }

    public Optional<Invitation> findById(final String id) {
        return invitationRepository.findById (id);
    }
}
