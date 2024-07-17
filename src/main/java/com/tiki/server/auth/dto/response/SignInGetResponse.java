package com.tiki.server.auth.dto.response;

import lombok.Builder;
import lombok.NonNull;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record SignInGetResponse(
        @NonNull String accessToken,
        @NonNull String refreshToken
) {

    public static SignInGetResponse from(String accessToken, String refreshToken) {
        return SignInGetResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    }
}
