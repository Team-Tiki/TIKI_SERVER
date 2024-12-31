package com.tiki.server.team.dto.request;

import jakarta.validation.constraints.NotNull;

public record TeamInformUpdateServiceRequest(
        @NotNull long memberId,
        @NotNull long teamId,
        @NotNull String teamName,
        @NotNull String teamIconUrl
) {
    public static TeamInformUpdateServiceRequest from(final TeamInformUpdateRequest request, final long memberId, final long teamId) {
        return new TeamInformUpdateServiceRequest(
                memberId,
                teamId,
                request.teamName(),
                request.teamUrl()
        );
    }
}
