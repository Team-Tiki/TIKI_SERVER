package com.tiki.server.team.dto.request;

import jakarta.validation.constraints.NotNull;

public record TeamInformUpdateRequest(
        @NotNull String teamName,
        @NotNull String teamUrl
) {
}
