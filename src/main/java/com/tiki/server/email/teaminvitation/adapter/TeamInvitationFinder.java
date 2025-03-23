package com.tiki.server.email.teaminvitation.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.email.teaminvitation.exception.TeamInvitationException;
import com.tiki.server.email.teaminvitation.entity.TeamInvitation;
import com.tiki.server.email.teaminvitation.repository.TeamInvitationRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.tiki.server.email.teaminvitation.messages.ErrorCode.INVALID_TEAM_INVITATION;

@RepositoryAdapter
@RequiredArgsConstructor
public class TeamInvitationFinder {

    private final TeamInvitationRepository teamInvitationRepository;

    public TeamInvitation findByInvitationId(final String invitationId) {
        return teamInvitationRepository.findById(invitationId)
                .orElseThrow(() -> new TeamInvitationException(INVALID_TEAM_INVITATION));
    }

    public List<TeamInvitation> findAllByIdStartingWith(final String teamId) {
        return teamInvitationRepository.findAllByIdStartingWith(teamId);
    }

    public Optional<TeamInvitation> presentById(final String id) {
        return teamInvitationRepository.presentById(id);
    }
}
