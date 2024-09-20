package com.tiki.server.emailVerification.service;

import com.tiki.server.common.entity.Email;
import com.tiki.server.emailVerification.adapter.EmailVerificationFinder;
import com.tiki.server.emailVerification.adapter.EmailVerificationSaver;
import com.tiki.server.emailVerification.dto.request.EmailRequest;
import com.tiki.server.emailVerification.dto.request.CodeVerificationRequest;
import com.tiki.server.emailVerification.domain.EmailVerification;
import com.tiki.server.emailVerification.domain.MailSender;
import com.tiki.server.emailVerification.exception.EmailVerificationException;
import com.tiki.server.member.adapter.MemberFinder;
import com.tiki.server.member.exception.MemberException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.tiki.server.emailVerification.message.ErrorCode.*;
import static com.tiki.server.member.message.ErrorCode.*;
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
        Email email = mailRequest.email();
        memberFinder.checkPresent(email);
        mailSaver.save(mailSender.sendVerificationMail(email, MAIL_SUBJECT_SIGN_UP));
    }

    @Transactional
    public void sendChangingPassword(EmailRequest mailRequest) {
        Email email = mailRequest.email();
        memberFinder.checkEmpty(email);
        mailSaver.save(mailSender.sendVerificationMail(email, MAIL_SUBJECT_CHANGING_PASSWORD));
    }

    public void checkCode(CodeVerificationRequest codeVerificationRequest) {
        Email email = codeVerificationRequest.email();
        EmailVerification EmailVerification = emailVerificationFinder.findById(email.getEmail());

        if (!EmailVerification.getCode().equals(codeVerificationRequest.code())) {
            throw new EmailVerificationException(INVALID_MATCHED);
        }
    }
}