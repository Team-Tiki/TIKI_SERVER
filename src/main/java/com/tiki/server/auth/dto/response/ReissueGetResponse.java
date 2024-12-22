package com.tiki.server.auth.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record ReissueGetResponse(
        @NotNull String accessToken
) {
    
    public static ReissueGetResponse from(String accessToken) {
        return ReissueGetResponse.builder().accessToken(accessToken).build();
    }
}
