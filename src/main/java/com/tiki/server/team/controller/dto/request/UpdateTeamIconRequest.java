package com.tiki.server.team.controller.dto.request;

import jakarta.validation.constraints.NotNull;

public record UpdateTeamIconRequest(
        @NotNull String iconImageUrl
) {
}
