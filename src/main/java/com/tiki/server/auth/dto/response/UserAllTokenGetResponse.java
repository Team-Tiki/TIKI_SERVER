package com.tiki.server.auth.dto.response;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record UserAllTokenGetResponse(
        @NonNull String accessToken,
        @NonNull String refreshToken
) {
    public static UserAllTokenGetResponse of(String accessToken, String refreshToken) {
        return UserAllTokenGetResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    }
}
