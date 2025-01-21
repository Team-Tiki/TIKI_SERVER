package com.tiki.server.memberteammanager.service.dto.response;

import com.tiki.server.common.entity.Position;
import jakarta.validation.constraints.NotNull;

public record MemberTeamInformGetResponse(
        @NotNull Position position,
        @NotNull String name
) {
    public static MemberTeamInformGetResponse of(final Position position, final String name) {
        return new MemberTeamInformGetResponse(position, name);
    }
}
