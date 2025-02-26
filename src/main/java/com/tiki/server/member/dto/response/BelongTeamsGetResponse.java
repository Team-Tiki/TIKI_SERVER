package com.tiki.server.member.dto.response;

import com.tiki.server.team.dto.response.TeamResponse;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record BelongTeamsGetResponse(
        @NotNull List<BelongTeamGetResponse> belongTeamGetResponses
) {

    public static BelongTeamsGetResponse from(final List<TeamResponse> teams) {
        return BelongTeamsGetResponse.builder()
                .belongTeamGetResponses(teams.stream().map(BelongTeamGetResponse::from).toList())
                .build();
    }

    @Builder(access = PRIVATE)
    public record BelongTeamGetResponse(
            @NotNull long id,
            @NotNull String name,
            @NotNull String iconImageUrl
    ) {
        public static BelongTeamGetResponse from(final TeamResponse team) {
            return BelongTeamGetResponse.builder()
                    .id(team.teamId())
                    .name(team.name())
                    .iconImageUrl(team.iconImageUrl())
                    .build();
        }
    }
}
