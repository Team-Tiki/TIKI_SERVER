package com.tiki.server.team.dto.request;

import com.tiki.server.team.controller.dto.request.UpdateTeamMemberAndTeamInformRequest;
import jakarta.validation.constraints.NotNull;

public record UpdateTeamMemberAndTeamInformServiceRequest(
        @NotNull String teamMemberName,
        @NotNull String teamName,
        @NotNull String teamIconUrl
) {
    public static UpdateTeamMemberAndTeamInformServiceRequest from(UpdateTeamMemberAndTeamInformRequest request) {
        return new UpdateTeamMemberAndTeamInformServiceRequest(
                request.teamMemberName(),
                request.teamName(),
                request.teamUrl()
        );
    }
}
