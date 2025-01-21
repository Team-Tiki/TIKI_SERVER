package com.tiki.server.member.dto.response;

import jakarta.validation.constraints.NotNull;

public record SignInResultGetResponse(
        @NotNull String accessToken
) {

    public static SignInResultGetResponse from(final String accessToken) {
        return new SignInResultGetResponse(accessToken);
    }
}