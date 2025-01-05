package com.tiki.server.email.teaminvitation.repository;

import com.tiki.server.email.teaminvitation.entity.TeamInvitation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TeamInvitationRepository extends JpaRepository<TeamInvitation, Long> {

    List<TeamInvitation> findByExpiredDateBefore(final LocalDate expiredDate);

    List<TeamInvitation> findAllByTeamId(final long teamId);
}
