package com.tiki.server.email.emailsender.service;

import com.tiki.server.email.Email;
import com.tiki.server.email.emailsender.entity.MailSender;
import com.tiki.server.email.emailsender.service.dto.EmailServiceRequest;
import com.tiki.server.email.emailverification.adapter.EmailVerificationSaver;
import com.tiki.server.email.emailverification.domain.EmailVerification;
import com.tiki.server.member.adapter.MemberFinder;
import com.tiki.server.member.entity.Member;
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
    public void sendSignUp(final EmailServiceRequest emailServiceRequest) {
        memberFinder.checkPresent(emailServiceRequest.email());
        EmailVerification emailVerification = EmailVerification.of(emailServiceRequest.email());
        mailSender.sendVerificationMail(
                emailVerification.getId(),
                emailVerification.getVerificationCode().getCode()
        );
        emailVerificationSaver.save(emailVerification);
    }

    @Transactional
    public void sendPasswordChanging(final EmailServiceRequest emailServiceRequest) {
        Member member = memberFinder.checkEmpty(emailServiceRequest.email());
        EmailVerification emailVerification = EmailVerification.of(emailServiceRequest.email());
        mailSender.sendPasswordChangingMail(
                emailVerification.getId(),
                emailVerification.getVerificationCode().getCode(),
                member.getName()
        );
        emailVerificationSaver.save(emailVerification);
    }
}
