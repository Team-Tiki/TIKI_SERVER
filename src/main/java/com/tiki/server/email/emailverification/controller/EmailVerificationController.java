package com.tiki.server.email.emailverification.controller;

import static com.tiki.server.common.dto.SuccessResponse.success;
import static com.tiki.server.common.support.UriGenerator.getUri;
import static com.tiki.server.email.emailverification.message.SuccessMessage.SUCCESS_VALIDATION;

import com.tiki.server.common.dto.BaseResponse;
import com.tiki.server.email.emailverification.dto.request.CodeVerificationRequest;
import com.tiki.server.email.emailverification.service.EmailVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/email/verification")
public class EmailVerificationController {

    private final EmailVerificationService emailVerificationService;

    @PostMapping("/checking")
    public ResponseEntity<BaseResponse> checkCode(@RequestBody CodeVerificationRequest codeVerificationRequest) {
        emailVerificationService.checkCode(codeVerificationRequest);
        return ResponseEntity.created(getUri("/")).body(success(SUCCESS_VALIDATION.getMessage()));
    }
}
