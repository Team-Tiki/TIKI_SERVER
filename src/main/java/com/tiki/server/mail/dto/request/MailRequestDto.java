package com.tiki.server.mail.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record MailRequestDto(
        @Email
        @NotEmpty(message = "이메일을 입력해 주세요")
        String address
) {
}