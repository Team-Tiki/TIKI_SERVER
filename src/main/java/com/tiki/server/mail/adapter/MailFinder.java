package com.tiki.server.mail.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.mail.entity.Mail;
import com.tiki.server.mail.repository.MailRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RepositoryAdapter
@RequiredArgsConstructor
public class MailFinder {

    private final MailRepository mailRepository;

    public Optional<Mail> findById(String address) {
        return mailRepository.findById(address);
    }
}
