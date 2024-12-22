package com.tiki.server.email.emailverification.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.email.emailverification.domain.EmailVerification;
import com.tiki.server.email.emailverification.repository.EmailVerificationRepository;
import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class EmailVerificationSaver {

    private final EmailVerificationRepository mailRepository;

    public void save(EmailVerification mail) {
        mailRepository.save(mail);
    }
}
