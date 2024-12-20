package com.tiki.server.member.dto.request;

import jakarta.validation.constraints.NotNull;

public record PasswordChangeRequest(
        @NotNull String email,
        @NotNull String password,
        @NotNull String passwordChecker
) {
}