package com.tiki.server.emailverification.controller;

import com.tiki.server.common.dto.BaseResponse;

import com.tiki.server.emailverification.controller.docs.EmailVerificationControllerDocs;
import com.tiki.server.emailverification.dto.request.EmailRequest;
import com.tiki.server.emailverification.dto.request.CodeVerificationRequest;
import com.tiki.server.emailverification.service.EmailVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.tiki.server.common.dto.SuccessResponse.success;
import static com.tiki.server.common.support.UriGenerator.getUri;
import static com.tiki.server.emailverification.message.SuccessMessage.SUCCESS_SEND_EMAIL;
import static com.tiki.server.emailverification.message.SuccessMessage.SUCCESS_VALIDATION;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/email-verification")
public class EmailVerificationController implements EmailVerificationControllerDocs {

    private final EmailVerificationService emailVerificationService;

    @PostMapping("/signup")
    public ResponseEntity<BaseResponse> sendSignUpMail(@RequestBody EmailRequest mailRequest) {
        emailVerificationService.sendSignUp(mailRequest);
        return ResponseEntity.created(getUri("/")).body(success(SUCCESS_SEND_EMAIL.getMessage()));
    }

    @PostMapping("/password")
    public ResponseEntity<BaseResponse> sendChangingPasswordMail(@RequestBody EmailRequest mailRequest) {
        emailVerificationService.sendChangingPassword(mailRequest);
        return ResponseEntity.created(getUri("/")).body(success(SUCCESS_SEND_EMAIL.getMessage()));
    }

    @PostMapping("/checking")
    public ResponseEntity<BaseResponse> checkCode(@RequestBody CodeVerificationRequest codeVerificationRequest) {
        emailVerificationService.checkCode(codeVerificationRequest);
        return ResponseEntity.created(getUri("/")).body(success(SUCCESS_VALIDATION.getMessage()));
    }
}
