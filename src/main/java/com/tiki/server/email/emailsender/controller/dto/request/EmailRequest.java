package com.tiki.server.email.emailsender.controller.dto.request;

import jakarta.validation.constraints.NotNull;

public record EmailRequest(
        @NotNull String email
) {
}