package com.tiki.server.email.emailsender.dto.request;

import lombok.NonNull;

public record EmailRequest(
       @NonNull String email
) {
}