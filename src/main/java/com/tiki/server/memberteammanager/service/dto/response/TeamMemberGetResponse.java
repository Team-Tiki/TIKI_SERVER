package com.tiki.server.memberteammanager.service.dto.response;

import com.tiki.server.common.entity.Position;
import com.tiki.server.memberteammanager.repository.projection.TeamMemberInformGetProjection;

public record TeamMemberGetResponse (
        String name,
        Position position,
        String email
){
    public static TeamMemberGetResponse from(TeamMemberInformGetProjection projection){
        return new TeamMemberGetResponse(projection.getMemberName(),projection.getMemberPosition(), projection.getMemberEmail());
    }
}
