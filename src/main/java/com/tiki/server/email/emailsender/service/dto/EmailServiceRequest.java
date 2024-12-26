package com.tiki.server.email.emailsender.service.dto;

import com.tiki.server.email.Email;
import jakarta.validation.constraints.NotNull;

public record EmailServiceRequest(
        @NotNull Email email
) {
    public static EmailServiceRequest from(final String email) {
        return new EmailServiceRequest(Email.from(email));
    }
}