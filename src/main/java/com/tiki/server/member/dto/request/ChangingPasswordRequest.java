package com.tiki.server.member.dto.request;

import lombok.NonNull;

public record ChangingPasswordRequest(
        @NonNull String email,
        @NonNull String password,
        @NonNull String passwordChecker
) {
}