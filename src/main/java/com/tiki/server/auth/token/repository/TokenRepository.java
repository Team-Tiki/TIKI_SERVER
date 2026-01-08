package com.tiki.server.auth.token.repository;

import com.tiki.server.auth.token.entity.Token;
import com.tiki.server.common.support.RedisRepository;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

@RedisRepository
public interface TokenRepository extends CrudRepository<Token, Long> {

    Optional<Token> findById(final long id);
}
