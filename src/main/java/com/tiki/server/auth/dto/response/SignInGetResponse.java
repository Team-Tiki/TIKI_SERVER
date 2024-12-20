package com.tiki.server.auth.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record SignInGetResponse(
        @NotNull String accessToken,
        @NotNull String refreshToken
) {

    public static SignInGetResponse from(String accessToken, String refreshToken) {
        return SignInGetResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    }
}
