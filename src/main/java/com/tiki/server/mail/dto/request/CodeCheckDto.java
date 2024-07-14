package com.tiki.server.mail.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record CodeCheckDto(
        @Email
        String address,
        @NotEmpty(message = "번호를 입력해 주세요")
        String code
) {
}
