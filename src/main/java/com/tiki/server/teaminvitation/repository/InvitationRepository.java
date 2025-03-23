package com.tiki.server.teaminvitation.repository;

import com.tiki.server.teaminvitation.entity.Invitation;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationRepository extends CrudRepository<Invitation, String> {

    List<Invitation> findAllByIdStartingWith(final String teamId);

    Optional<Invitation> findById (final String id);
}
