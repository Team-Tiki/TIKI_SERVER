package com.tiki.server.auth.dto.response;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record LoginResponse(
        @NonNull String accessToken,
        @NonNull String refreshToken
) {
    public static LoginResponse of(String accessToken, String refreshToken) {
        return LoginResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    }
}
