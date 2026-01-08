package com.tiki.server.teaminvitation.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.teaminvitation.entity.TeamInvitation;
import com.tiki.server.teaminvitation.exception.TeamInvitationException;
import com.tiki.server.teaminvitation.messages.ErrorCode;
import com.tiki.server.teaminvitation.repository.TeamInvitationRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RepositoryAdapter
@RequiredArgsConstructor
public class TeamInvitationFinder {

    private final TeamInvitationRepository teamInvitationRepository;

    public TeamInvitation findByInvitationId(final String invitationId) {
        return teamInvitationRepository.findById(invitationId)
                .orElseThrow(() -> new TeamInvitationException(ErrorCode.INVALID_TEAM_INVITATION));
    }

    public List<TeamInvitation> findAllByIdStartingWith(final String teamId) {
        return teamInvitationRepository.findAllByIdStartingWith(teamId);
    }

    public Optional<TeamInvitation> findById(final String id) {
        return teamInvitationRepository.findById(id);
    }
}
