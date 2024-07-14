package com.tiki.server.mail.dto.request;

import jakarta.validation.constraints.Email;
import lombok.NonNull;

public record MailRequest(
        @Email
        @NonNull
        String address
) {
}