package com.tiki.server.email.emailverification.dto.request;

import lombok.NonNull;

public record CodeVerificationRequest(
        @NonNull
        String email,
        @NonNull
        String code
) {
}
