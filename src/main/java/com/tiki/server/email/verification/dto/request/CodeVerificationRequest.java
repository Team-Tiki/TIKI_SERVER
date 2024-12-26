package com.tiki.server.email.verification.dto.request;

import jakarta.validation.constraints.NotNull;

public record CodeVerificationRequest(
        @NotNull String email,
        @NotNull String code
) {
}
