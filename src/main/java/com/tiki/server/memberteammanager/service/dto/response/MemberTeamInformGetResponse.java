package com.tiki.server.memberteammanager.service.dto.response;

import com.tiki.server.common.entity.Position;

public record MemberTeamInformGetResponse(
        Position position,
        String name
) {
    public static MemberTeamInformGetResponse of(final Position position, final String name) {
        return new MemberTeamInformGetResponse(position, name);
    }
}
