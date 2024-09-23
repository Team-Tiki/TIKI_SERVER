package com.tiki.server.emailVerification.service;

import com.tiki.server.common.entity.Email;
import com.tiki.server.emailVerification.adapter.EmailVerificationFinder;
import com.tiki.server.emailVerification.adapter.EmailVerificationSaver;
import com.tiki.server.emailVerification.dto.request.EmailRequest;
import com.tiki.server.emailVerification.dto.request.CodeVerificationRequest;
import com.tiki.server.emailVerification.domain.EmailVerification;
import com.tiki.server.emailVerification.domain.MailSender;
import com.tiki.server.member.adapter.MemberFinder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.tiki.server.emailVerification.constants.EmailConstants.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmailVerificationService {

    private final MemberFinder memberFinder;
    private final EmailVerificationFinder emailVerificationFinder;
    private final EmailVerificationSaver mailSaver;
    private final MailSender mailSender;

    @Transactional
    public void sendSignUp(EmailRequest mailRequest) {
        Email email = Email.from(mailRequest.email());
        memberFinder.checkPresent(email);
        mailSaver.save(mailSender.sendVerificationMail(email, MAIL_SUBJECT_SIGN_UP));
    }

    @Transactional
    public void sendChangingPassword(EmailRequest mailRequest) {
        Email email = Email.from(mailRequest.email());
        memberFinder.checkEmpty(email);
        mailSaver.save(mailSender.sendVerificationMail(email, MAIL_SUBJECT_CHANGING_PASSWORD));
    }

    public void checkCode(CodeVerificationRequest codeVerificationRequest) {
        Email email = Email.from(codeVerificationRequest.email());
        EmailVerification emailVerification = emailVerificationFinder.findById(email.getEmail());
        emailVerification.verify(codeVerificationRequest.code());
    }
}