package com.tiki.server.auth.token.adapter;

import com.tiki.server.auth.token.entity.Token;
import com.tiki.server.auth.token.repository.TokenRepository;
import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.mail.entity.Mail;
import com.tiki.server.mail.repository.MailRepository;
import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class TokenSaver {

    private final TokenRepository tokenRepository;

    public void save(long id,String refreshToken) {
        tokenRepository.save(Token.of(id,refreshToken));
    }
}
