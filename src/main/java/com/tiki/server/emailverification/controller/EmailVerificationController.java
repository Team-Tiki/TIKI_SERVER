package com.tiki.server.emailverification.controller;

import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.emailverification.controller.docs.EmailVerificationControllerDocs;
import com.tiki.server.emailverification.dto.request.EmailRequest;
import com.tiki.server.emailverification.dto.request.CodeVerificationRequest;
import com.tiki.server.emailverification.service.EmailVerificationService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.tiki.server.emailverification.message.SuccessMessage.SUCCESS_SEND_EMAIL;
import static com.tiki.server.emailverification.message.SuccessMessage.SUCCESS_VALIDATION;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/email-verification")
public class EmailVerificationController implements EmailVerificationControllerDocs {

    private final EmailVerificationService emailVerificationService;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public SuccessResponse<?> sendSignUpMail(@RequestBody final EmailRequest mailRequest) {
        emailVerificationService.sendSignUp(mailRequest);
        return SuccessResponse.success(SUCCESS_SEND_EMAIL.getMessage());
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/password")
    public SuccessResponse<?> sendChangingPasswordMail(@RequestBody final EmailRequest mailRequest) {
        emailVerificationService.sendChangingPassword(mailRequest);
        return SuccessResponse.success(SUCCESS_SEND_EMAIL.getMessage());
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/checking")
    public SuccessResponse<?> checkCode(@RequestBody final CodeVerificationRequest codeVerificationRequest) {
        emailVerificationService.checkCode(codeVerificationRequest);
        return SuccessResponse.success(SUCCESS_VALIDATION.getMessage());
    }
}
