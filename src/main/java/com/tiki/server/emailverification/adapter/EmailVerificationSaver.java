package com.tiki.server.emailverification.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.emailverification.domain.EmailVerification;
import com.tiki.server.emailverification.repository.EmailVerificationRepository;
import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class EmailVerificationSaver {

    private final EmailVerificationRepository mailRepository;

    public void save(final EmailVerification mail) {
        mailRepository.save(mail);
    }
}
