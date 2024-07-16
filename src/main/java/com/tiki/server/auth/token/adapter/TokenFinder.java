package com.tiki.server.auth.token.adapter;

import com.tiki.server.auth.token.entity.Token;
import com.tiki.server.auth.token.repository.TokenRepository;
import com.tiki.server.common.support.RepositoryAdapter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RepositoryAdapter
@RequiredArgsConstructor
public class TokenFinder {

    private final TokenRepository tokenRepository;

    public Optional<Token> findById(long id) {
        return tokenRepository.findById(id);
    }
}
