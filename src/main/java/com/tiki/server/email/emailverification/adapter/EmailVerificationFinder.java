package com.tiki.server.email.emailverification.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.email.emailverification.domain.EmailVerification;
import com.tiki.server.email.emailverification.exception.EmailVerificationException;
import com.tiki.server.email.emailverification.repository.EmailVerificationRepository;
import lombok.RequiredArgsConstructor;

import static com.tiki.server.email.emailverification.message.ErrorCode.INVALID_REQUEST;

@RepositoryAdapter
@RequiredArgsConstructor
public class EmailVerificationFinder {

    private final EmailVerificationRepository mailRepository;

    public EmailVerification findById(String email) {
        return mailRepository.findById(email).orElseThrow(() -> new EmailVerificationException(INVALID_REQUEST));
    }
}
