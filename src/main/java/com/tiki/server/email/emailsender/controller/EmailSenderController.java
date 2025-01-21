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
    public SuccessResponse<?> sendSignUpMail(@RequestBody final EmailRequest mailRequest) {
        emailSenderService.sendSignUp(EmailServiceRequest.from(mailRequest.email()));
        return SuccessResponse.success(SuccessMessage.SUCCESS_SEND_EMAIL.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/verification/password")
    public SuccessResponse<?> sendChangingPasswordMail(@RequestBody final EmailRequest mailRequest) {
        emailSenderService.sendPasswordChanging(EmailServiceRequest.from(mailRequest.email()));
        return SuccessResponse.success(SuccessMessage.SUCCESS_SEND_EMAIL.getMessage());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/invitation/team/{teamId}")
    public SuccessResponse<?> sendInvitationMail(
            final Principal principal,
            @PathVariable final long teamId,
            @RequestBody final EmailRequest targetEmailRequest
    ) {
        long memberId = Long.parseLong(principal.getName());
        emailSenderService.createTeamInvitation(
                TeamInvitationCreateServiceRequest.of(targetEmailRequest.email(), teamId, memberId));
        return SuccessResponse.success(SuccessMessage.SUCCESS_SEND_EMAIL.getMessage());
    }
}
