package com.tiki.server.mail.dto.request;

import jakarta.validation.constraints.Email;
import lombok.NonNull;

public record CodeCheck(
        @Email
        String email,
        @NonNull
        String code
) {
}
