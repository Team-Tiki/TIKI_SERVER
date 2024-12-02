package com.tiki.server.memberteammanager.service.dto.response;

import com.tiki.server.common.entity.Position;

public record MemberTeamPositionGetResponse(
        Position position
) {
    public static MemberTeamPositionGetResponse from(Position position) {
        return new MemberTeamPositionGetResponse(position);
    }
}
