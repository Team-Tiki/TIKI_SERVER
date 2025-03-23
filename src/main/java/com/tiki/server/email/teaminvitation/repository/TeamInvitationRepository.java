package com.tiki.server.email.teaminvitation.repository;

import com.tiki.server.email.teaminvitation.entity.TeamInvitation;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface TeamInvitationRepository extends CrudRepository<TeamInvitation, String> {

    List<TeamInvitation> findAllByIdStartingWith(final String teamId);

    Optional<TeamInvitation> presentById(final String id);
}
