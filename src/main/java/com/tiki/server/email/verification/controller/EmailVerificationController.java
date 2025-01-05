package com.tiki.server.email.verification.controller;

import static com.tiki.server.email.verification.message.SuccessMessage.SUCCESS_VALIDATION;

import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.email.verification.dto.request.CodeVerificationRequest;
import com.tiki.server.email.verification.service.EmailVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/email/verification")
public class EmailVerificationController {

    private final EmailVerificationService emailVerificationService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/checking")
    public SuccessResponse<?> checkCode(@RequestBody CodeVerificationRequest codeVerificationRequest) {
        emailVerificationService.checkCode(codeVerificationRequest);
        return SuccessResponse.success(SUCCESS_VALIDATION.getMessage());
    }
}
