package com.tiki.server.email.teaminvitation.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.email.teaminvitation.exception.TeamInvitationException;
import com.tiki.server.email.teaminvitation.entity.TeamInvitation;
import com.tiki.server.email.teaminvitation.repository.TeamInvitationRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.tiki.server.email.teaminvitation.messages.ErrorCode.INVALID_TEAM_INVITATION;

@RepositoryAdapter
@RequiredArgsConstructor
public class TeamInvitationFinder {

    private final TeamInvitationRepository teamInvitationRepository;

    public TeamInvitation findByInvitationId(final long invitationId) {
        return teamInvitationRepository.findById(invitationId)
                .orElseThrow(() -> new TeamInvitationException(INVALID_TEAM_INVITATION));
    }

    public List<TeamInvitation> findByExpiredDate(final LocalDate expiredDate) {
        return teamInvitationRepository.findByExpiredDateBefore(expiredDate);
    }

    public List<TeamInvitation> findAllByTeamId(final long teamId) {
        return teamInvitationRepository.findAllByTeamId(teamId);
    }
}
