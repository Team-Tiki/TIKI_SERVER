package com.tiki.server.mail.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.mail.entity.Mail;
import com.tiki.server.mail.repository.MailRepository;
import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class MailSaver {

    private final MailRepository mailRepository;

    public void save(Mail mail) {
        mailRepository.save(mail);
    }
}
