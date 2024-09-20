package com.tiki.server.emailVerification.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.emailVerification.domain.EmailVerification;
import com.tiki.server.emailVerification.exception.EmailVerificationException;
import com.tiki.server.emailVerification.repository.EmailVerificationRepository;
import lombok.RequiredArgsConstructor;

import static com.tiki.server.emailVerification.message.ErrorCode.INVALID_REQUEST;

@RepositoryAdapter
@RequiredArgsConstructor
public class EmailVerificationFinder {

    private final EmailVerificationRepository mailRepository;

    public EmailVerification findById(String email) {
        return mailRepository.findById(email).orElseThrow(() -> new EmailVerificationException(INVALID_REQUEST));
    }
}
