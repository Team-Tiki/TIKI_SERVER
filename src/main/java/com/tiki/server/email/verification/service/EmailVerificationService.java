package com.tiki.server.email.verification.service;

import com.tiki.server.email.verification.adapter.EmailVerificationFinder;
import com.tiki.server.email.verification.domain.EmailVerification;
import com.tiki.server.email.verification.dto.request.CodeVerificationRequest;
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