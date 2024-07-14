package com.tiki.server.mail.controller;

import com.tiki.server.common.dto.BaseResponse;

import com.tiki.server.mail.dto.request.CodeCheck;
import com.tiki.server.mail.dto.request.MailRequest;
import com.tiki.server.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.tiki.server.common.dto.SuccessResponse.success;
import static com.tiki.server.common.support.UriGenerator.getUri;
import static com.tiki.server.mail.message.SuccessMessage.SUCCESS_SEND_EMAIL;
import static com.tiki.server.mail.message.SuccessMessage.SUCCESS_VALIDATION;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mail")
public class MailController {

    private final MailService mailService;

    @PostMapping("/signup")
    public ResponseEntity<BaseResponse> sendSignUpMail(@RequestBody MailRequest mailRequest) {
        mailService.sendSignUp(mailRequest);
        return ResponseEntity.created(getUri("/")).body(success(SUCCESS_SEND_EMAIL.getMessage()));
    }

    @PostMapping("/password")
    public ResponseEntity<BaseResponse> sendChangingPasswordMail(@RequestBody MailRequest mailRequest) {
        mailService.sendChangingPassword(mailRequest);
        return ResponseEntity.created(getUri("/")).body(success(SUCCESS_SEND_EMAIL.getMessage()));
    }

    @PostMapping("/checking")
    public ResponseEntity<BaseResponse> checkCode(@RequestBody CodeCheck codeCheck) {
        mailService.checkCode(codeCheck);
        return ResponseEntity.created(getUri("/")).body(success(SUCCESS_VALIDATION.getMessage()));
    }

}
