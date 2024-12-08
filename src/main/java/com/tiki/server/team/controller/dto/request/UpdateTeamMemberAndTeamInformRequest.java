package com.tiki.server.team.controller.dto.request;

import jakarta.validation.constraints.NotNull;

public record UpdateTeamMemberAndTeamInformRequest(
        @NotNull String teamMemberName,
        @NotNull String teamName,
        @NotNull String teamUrl
) {
}
