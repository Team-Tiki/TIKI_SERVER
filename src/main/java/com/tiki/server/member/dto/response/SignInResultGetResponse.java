package com.tiki.server.member.dto.response;

import lombok.NonNull;

public record SignInResultGetResponse(
        @NonNull String accessToken
) {

    public static SignInResultGetResponse from(
            final String accessToken
    ) {
        return new SignInResultGetResponse(accessToken);
    }
}