package com.tiki.server.emailverification.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.emailverification.domain.EmailVerification;
import com.tiki.server.emailverification.exception.EmailVerificationException;
import com.tiki.server.emailverification.repository.EmailVerificationRepository;
import lombok.RequiredArgsConstructor;

import static com.tiki.server.emailverification.message.ErrorCode.INVALID_REQUEST;

@RepositoryAdapter
@RequiredArgsConstructor
public class EmailVerificationFinder {

    private final EmailVerificationRepository mailRepository;

    public EmailVerification findById(final String email) {
        return mailRepository.findById(email).orElseThrow(() -> new EmailVerificationException(INVALID_REQUEST));
    }
}
