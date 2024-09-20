package com.tiki.server.emailVerification.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.emailVerification.domain.EmailVerification;
import com.tiki.server.emailVerification.repository.EmailVerificationRepository;
import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class EmailVerificationSaver {

    private final EmailVerificationRepository mailRepository;

    public void save(EmailVerification mail) {
        mailRepository.save(mail);
    }
}
