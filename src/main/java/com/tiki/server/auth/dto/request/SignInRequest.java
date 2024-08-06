package com.tiki.server.auth.dto.request;

import lombok.NonNull;

public record SignInRequest(
        @NonNull String email,
        @NonNull String password
) {
}
