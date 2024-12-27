package com.tiki.server.team.dto.request;

import jakarta.validation.constraints.NotNull;

public record TeamMemberAndTeamInformUpdateServiceRequest(
        @NotNull String teamMemberName,
        @NotNull String teamName,
        @NotNull String teamIconUrl
) {
    public static TeamMemberAndTeamInformUpdateServiceRequest from(final TeamMemberAndTeamInformUpdateRequest request) {
        return new TeamMemberAndTeamInformUpdateServiceRequest(
                request.teamMemberName(),
                request.teamName(),
                request.teamUrl()
        );
    }
}
