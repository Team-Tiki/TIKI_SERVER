package com.tiki.server.auth.token.adapter;

import com.tiki.server.auth.exception.AuthException;
import com.tiki.server.auth.token.entity.Token;
import com.tiki.server.auth.token.repository.TokenRepository;
import com.tiki.server.common.support.RepositoryAdapter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.tiki.server.auth.message.ErrorCode.UNAUTHORIZED_USER;

@RepositoryAdapter
@RequiredArgsConstructor
public class TokenFinder {

    private final TokenRepository tokenRepository;

    public Token findById(final long id) {
        return tokenRepository.findById(id).orElseThrow(() -> new AuthException(UNAUTHORIZED_USER));
    }
}
