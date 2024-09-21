package com.tiki.server.emailVerification.dto.request;

import lombok.NonNull;

public record EmailRequest(
       @NonNull String email
) {
}