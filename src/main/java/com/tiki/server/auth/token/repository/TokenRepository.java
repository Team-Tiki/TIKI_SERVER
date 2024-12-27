package com.tiki.server.auth.token.repository;

import com.tiki.server.auth.token.entity.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends CrudRepository<Token, Long> {

    Optional<Token> findById(final long id);
}
