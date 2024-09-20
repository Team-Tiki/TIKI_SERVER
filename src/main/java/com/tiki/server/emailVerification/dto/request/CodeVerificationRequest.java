package com.tiki.server.emailVerification.dto.request;

import com.tiki.server.common.entity.Email;
import lombok.NonNull;

public record CodeVerificationRequest(
        @NonNull
        Email email,
        @NonNull
        String code
) {
}
