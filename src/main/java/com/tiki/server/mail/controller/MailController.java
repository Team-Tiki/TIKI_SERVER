package com.tiki.server.mail.controller;

import com.tiki.server.common.dto.BaseResponse;
import com.tiki.server.mail.dto.request.EmailRequestDto;
import com.tiki.server.mail.service.EmailService;
import com.tiki.server.mail.service.MailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.tiki.server.common.dto.SuccessResponse.success;
import static com.tiki.server.common.support.UriGenerator.getUri;
import static com.tiki.server.mail.message.SuccessMessage.SUCCESS_SEND_EMAIL;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mail")
public class MailController {

    private final MailService mailService;

    @PostMapping
    public ResponseEntity<BaseResponse> sendJoinMail(@RequestBody EmailRequestDto emailRequestDto) throws MessagingException {
        mailService.sendMail(emailRequestDto);
        return ResponseEntity.created(getUri("/")).body(success(SUCCESS_SEND_EMAIL.getMessage()));
    }
}
