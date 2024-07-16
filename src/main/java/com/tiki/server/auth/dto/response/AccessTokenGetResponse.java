package com.tiki.server.auth.dto.response;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record AccessTokenGetResponse(
        @NonNull String accessToken
) {
    public static AccessTokenGetResponse from(String accessToken) {
        return AccessTokenGetResponse.builder().accessToken(accessToken).build();
    }
}
