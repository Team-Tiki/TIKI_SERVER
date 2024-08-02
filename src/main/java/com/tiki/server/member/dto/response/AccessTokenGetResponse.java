package com.tiki.server.member.dto.response;

import lombok.NonNull;

public record AccessTokenGetResponse(
        @NonNull String accessToken
) {

    public static AccessTokenGetResponse from(
            final String accessToken
    ) {
        return new AccessTokenGetResponse(accessToken);
    }
}