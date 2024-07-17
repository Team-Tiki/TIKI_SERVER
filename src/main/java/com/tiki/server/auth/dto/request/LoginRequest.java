package com.tiki.server.auth.dto.request;

import lombok.NonNull;

public record LoginRequest(
        @NonNull String email,
        @NonNull String password
) {
}
