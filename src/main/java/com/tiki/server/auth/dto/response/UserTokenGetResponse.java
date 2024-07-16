package com.tiki.server.auth.dto.response;

import lombok.Builder;
import lombok.NonNull;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record UserTokenGetResponse(
        @NonNull String accessToken
) {
    public static UserTokenGetResponse from(String accessToken) {
        return UserTokenGetResponse.builder().accessToken(accessToken).build();
    }
}
