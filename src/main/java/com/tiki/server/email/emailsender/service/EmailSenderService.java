package com.tiki.server.email.emailsender.service;

import static com.tiki.server.email.emailsender.constants.Constants.MAIL_SUBJECT_CHANGING_PASSWORD;
import static com.tiki.server.email.emailsender.constants.Constants.MAIL_SUBJECT_SIGN_UP;

import com.tiki.server.email.Email;
import com.tiki.server.email.emailsender.dto.request.EmailRequest;
import com.tiki.server.email.emailsender.entity.MailSender;
import com.tiki.server.email.emailverification.adapter.EmailVerificationSaver;
import com.tiki.server.member.adapter.MemberFinder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmailSenderService {

    private final MemberFinder memberFinder;
    private final EmailVerificationSaver emailVerificationSaver;
    private final MailSender mailSender;

    @Transactional
    public void sendSignUp(EmailRequest mailRequest) {
        Email email = Email.from(mailRequest.email());
        memberFinder.checkPresent(email);
        emailVerificationSaver.save(mailSender.sendVerificationMail(email, MAIL_SUBJECT_SIGN_UP));
    }

    @Transactional
    public void sendChangingPassword(EmailRequest mailRequest) {
        Email email = Email.from(mailRequest.email());
        memberFinder.checkEmpty(email);
        emailVerificationSaver.save(mailSender.sendVerificationMail(email, MAIL_SUBJECT_CHANGING_PASSWORD));
    }
}