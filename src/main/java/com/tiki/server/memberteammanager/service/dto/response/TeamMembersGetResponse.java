package com.tiki.server.memberteammanager.service.dto.response;

import com.tiki.server.memberteammanager.repository.projection.TeamMemberInformGetProjection;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record TeamMembersGetResponse(
        @NotNull List<TeamMemberGetResponse> teamMemberGetResponses
) {
    public TeamMembersGetResponse(List<TeamMemberGetResponse> teamMemberGetResponses){
        this.teamMemberGetResponses = teamMemberGetResponses;
    }

    public static TeamMembersGetResponse from(List<TeamMemberInformGetProjection> projections){
        return new TeamMembersGetResponse(projections.stream().map(TeamMemberGetResponse::from).toList());
    }
}
