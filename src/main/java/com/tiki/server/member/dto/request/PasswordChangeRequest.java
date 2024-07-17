package com.tiki.server.member.dto.request;

import lombok.NonNull;

public record PasswordChangeRequest(
        @NonNull String email,
        @NonNull String password,
        @NonNull String passwordChecker
) {
}