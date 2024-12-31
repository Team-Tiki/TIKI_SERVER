package com.tiki.server.email.emailsender.controller;

import com.tiki.server.common.dto.BaseResponse;

import com.tiki.server.email.emailsender.controller.docs.EmailSenderControllerDocs;
import com.tiki.server.email.emailsender.controller.dto.request.EmailRequest;
import com.tiki.server.email.emailsender.message.SuccessMessage;
import com.tiki.server.email.emailsender.service.EmailSenderService;
import com.tiki.server.email.emailsender.service.dto.EmailServiceRequest;
import com.tiki.server.email.emailsender.service.dto.TeamInvitationCreateServiceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static com.tiki.server.common.dto.SuccessResponse.success;
import static com.tiki.server.common.support.UriGenerator.getUri;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/email")
public class EmailSenderController implements EmailSenderControllerDocs {

    private final EmailSenderService emailSenderService;

    @PostMapping("/verification/signup")
    public ResponseEntity<BaseResponse> sendSignUpMail(@RequestBody EmailRequest mailRequest) {
        emailSenderService.sendSignUp(EmailServiceRequest.from(mailRequest.email()));
        return ResponseEntity.created(getUri("/")).body(success(SuccessMessage.SUCCESS_SEND_EMAIL.getMessage()));
    }

    @PostMapping("/verification/password")
    public ResponseEntity<BaseResponse> sendChangingPasswordMail(@RequestBody EmailRequest mailRequest) {
        emailSenderService.sendPasswordChanging(EmailServiceRequest.from(mailRequest.email()));
        return ResponseEntity.created(getUri("/")).body(success(SuccessMessage.SUCCESS_SEND_EMAIL.getMessage()));
    }

    @PostMapping("/invitation/team/{teamId}")
    public ResponseEntity<BaseResponse> sendInvitationMail(
            Principal principal,
            @PathVariable long teamId,
            @RequestBody EmailRequest emailRequest
    ) {
        long memberId = Long.parseLong(principal.getName());
        emailSenderService.createTeamInvitation(TeamInvitationCreateServiceRequest.of(emailRequest.email(),teamId,memberId));
        return ResponseEntity.created(getUri("/")).body(success(SuccessMessage.SUCCESS_SEND_EMAIL.getMessage()));
    }
}
