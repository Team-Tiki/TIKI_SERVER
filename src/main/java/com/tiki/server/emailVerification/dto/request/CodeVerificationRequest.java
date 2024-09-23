package com.tiki.server.emailVerification.dto.request;

import lombok.NonNull;

public record CodeVerificationRequest(
        @NonNull
        String email,
        @NonNull
        String code
) {
}
