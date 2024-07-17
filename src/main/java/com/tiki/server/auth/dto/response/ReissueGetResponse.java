package com.tiki.server.auth.dto.response;

import lombok.Builder;
import lombok.NonNull;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record ReissueGetResponse(
        @NonNull String accessToken
) {
    
    public static ReissueGetResponse from(String accessToken) {
        return ReissueGetResponse.builder().accessToken(accessToken).build();
    }
}
