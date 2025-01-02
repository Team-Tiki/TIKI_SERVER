package com.tiki.server.email.emailsender.controller;

import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.email.emailsender.controller.docs.EmailSenderControllerDocs;
import com.tiki.server.email.emailsender.controller.dto.request.EmailRequest;
import com.tiki.server.email.emailsender.message.SuccessMessage;
import com.tiki.server.email.emailsender.service.EmailSenderService;
import com.tiki.server.email.emailsender.service.dto.EmailServiceRequest;
import com.tiki.server.email.emailsender.service.dto.TeamInvitationCreateServiceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/email")
public class EmailSenderController implements EmailSenderControllerDocs {

    private final EmailSenderService emailSenderService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/verification/signup")
    public SuccessResponse<?> sendSignUpMail(@RequestBody EmailRequest mailRequest) {
        emailSenderService.sendSignUp(EmailServiceRequest.from(mailRequest.email()));
        return SuccessResponse.success(SuccessMessage.SUCCESS_SEND_EMAIL.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/verification/password")
    public SuccessResponse<?> sendChangingPasswordMail(@RequestBody EmailRequest mailRequest) {
        emailSenderService.sendPasswordChanging(EmailServiceRequest.from(mailRequest.email()));
        return SuccessResponse.success(SuccessMessage.SUCCESS_SEND_EMAIL.getMessage());
    }

    @PostMapping("/invitation/team/{teamId}")
    public SuccessResponse<?> sendInvitationMail(
            Principal principal,
            @PathVariable long teamId,
            @RequestBody EmailRequest emailRequest
    ) {
        long memberId = Long.parseLong(principal.getName());
        emailSenderService.createTeamInvitation(
                TeamInvitationCreateServiceRequest.of(emailRequest.email(), teamId, memberId));
        return SuccessResponse.success(SuccessMessage.SUCCESS_SEND_EMAIL.getMessage());
    }
}
