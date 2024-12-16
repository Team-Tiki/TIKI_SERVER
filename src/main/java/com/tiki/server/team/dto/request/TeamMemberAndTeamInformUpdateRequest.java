package com.tiki.server.team.dto.request;

import jakarta.validation.constraints.NotNull;

public record TeamMemberAndTeamInformUpdateRequest(
        @NotNull String teamMemberName,
        @NotNull String teamName,
        @NotNull String teamUrl
) {
}
