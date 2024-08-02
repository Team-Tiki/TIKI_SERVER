package com.tiki.server.member.dto.response;

public record AccessTokenGetResponse(
        String accessToken
) {
    public static AccessTokenGetResponse of(
            final String accessToken
    ) {
        return new AccessTokenGetResponse(accessToken);
    }
}