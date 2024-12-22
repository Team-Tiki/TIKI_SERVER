package com.tiki.server.email.emailsender.dto.request;

import jakarta.validation.constraints.NotNull;

public record EmailRequest(
       @NotNull String email
) {
}