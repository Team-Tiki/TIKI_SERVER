package com.tiki.server.teaminvitation.repository;

import com.tiki.server.common.support.RedisRepository;
import com.tiki.server.teaminvitation.entity.TeamInvitation;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@RedisRepository
public interface TeamInvitationRepository extends CrudRepository<TeamInvitation, String> {

    List<TeamInvitation> findAllByIdStartingWith(final String teamId);

    Optional<TeamInvitation> findById (final String id);
}
