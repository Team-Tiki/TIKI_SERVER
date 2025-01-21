package com.tiki.server.member.dto.response;

import com.tiki.server.team.entity.Team;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record BelongTeamsGetResponse(
        @NotNull List<BelongTeamGetResponse> belongTeamGetResponses
) {

    public static BelongTeamsGetResponse from(final List<Team> teams) {
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
        public static BelongTeamGetResponse from(final Team team) {
            return BelongTeamGetResponse.builder()
                    .id(team.getId())
                    .name(team.getName())
                    .iconImageUrl(team.getIconImageUrl())
                    .build();
        }
    }
}
