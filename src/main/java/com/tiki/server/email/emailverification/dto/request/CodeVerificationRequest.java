package com.tiki.server.email.emailverification.dto.request;

import jakarta.validation.constraints.NotNull;

public record CodeVerificationRequest(
        @NotNull String email,
        @NotNull String code
) {
}
