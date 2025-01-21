package com.tiki.server.memberteammanager.service.dto.response;

import com.tiki.server.common.entity.Position;
import com.tiki.server.memberteammanager.repository.projection.TeamMemberInformGetProjection;
import jakarta.validation.constraints.NotNull;

public record TeamMemberGetResponse (
        @NotNull String name,
        @NotNull Position position,
        @NotNull String email
){
    public static TeamMemberGetResponse from(TeamMemberInformGetProjection projection){
        return new TeamMemberGetResponse(projection.getMemberName(),projection.getMemberPosition(), projection.getMemberEmail());
    }
}
