package com.tiki.server.email.emailverification.service;

import com.tiki.server.email.emailverification.adapter.EmailVerificationFinder;
import com.tiki.server.email.emailverification.domain.EmailVerification;
import com.tiki.server.email.emailverification.dto.request.CodeVerificationRequest;
import com.tiki.server.email.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmailVerificationService {

    private final EmailVerificationFinder emailVerificationFinder;

    public void checkCode(CodeVerificationRequest codeVerificationRequest) {
        Email email = Email.from(codeVerificationRequest.email());
        EmailVerification emailVerification = emailVerificationFinder.findById(email.getEmail());
        emailVerification.verify(codeVerificationRequest.code());
    }
}