package com.tiki.server.email.teaminvitation.repository;

import com.tiki.server.email.Email;
import com.tiki.server.email.teaminvitation.entity.TeamInvitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamInvitationRepository extends JpaRepository<TeamInvitation, Long> {

    TeamInvitation findByTeamIdAndEmail(final long teamId, final Email email);
}
