package com.tiki.server.emailverification.dto.request;

import jakarta.validation.constraints.NotNull;

public record CodeVerificationRequest(
        @NotNull String email,
        @NotNull String code
) {
}
