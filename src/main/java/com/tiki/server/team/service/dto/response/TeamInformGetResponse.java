package com.tiki.server.team.service.dto.response;

import com.tiki.server.common.entity.University;
import com.tiki.server.team.entity.Team;
import jakarta.validation.constraints.NotNull;

public record TeamInformGetResponse(
        @NotNull String teamName,
        @NotNull University university,
        @NotNull String teamIconUrl
) {

    public static TeamInformGetResponse from(final Team team) {
        return new TeamInformGetResponse(team.getName(),team.getUniv(), team.getIconImageUrl());
    }
}
