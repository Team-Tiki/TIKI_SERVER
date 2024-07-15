package com.tiki.server.memberteammanager.dto.response;

import static lombok.AccessLevel.PRIVATE;
import lombok.Builder;

@Builder(access = PRIVATE)
public record BelongTeamResponse(
        long id,
        String name,
        String iconImageUrl
) {
    public static BelongTeamResponse from(Mem)
}
