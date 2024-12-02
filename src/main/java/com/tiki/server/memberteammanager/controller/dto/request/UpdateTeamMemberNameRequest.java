package com.tiki.server.memberteammanager.controller.dto.request;

import jakarta.validation.constraints.NotNull;

public record UpdateTeamMemberNameRequest(
        @NotNull String newName
) {
}
