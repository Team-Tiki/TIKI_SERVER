package com.tiki.server.memberteammanager.service.dto.response;

import com.tiki.server.memberteammanager.repository.projection.NameAndEmailProjection;
import java.util.List;

public record TeamMembersGetResponse(
        List<TeamMemberGetResponse> teamMemberGetResponses
) {
    public TeamMembersGetResponse(List<NameAndEmailProjection> projections){
        this.teamMemberGetResponses = projections.stream().map(TeamMemberGetResponse::from).toList();
    }
}
