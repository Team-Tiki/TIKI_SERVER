package com.tiki.server.team.service.dto.response;

import com.tiki.server.common.entity.University;
import com.tiki.server.team.entity.Team;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TeamInformGetResponse(
        @NotNull String teamName,
        @NotNull University university,
        @NotNull String teamIconUrl,
        @NotNull LocalDate namingUpdatedAt
        ) {

    public static TeamInformGetResponse from(final Team team) {
        return new TeamInformGetResponse(team.getName(),team.getUniv(), team.getIconImageUrl(),team.getNamingUpdatedAt());
    }
}
