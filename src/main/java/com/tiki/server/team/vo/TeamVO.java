package com.tiki.server.team.vo;

import com.tiki.server.common.entity.University;
import com.tiki.server.team.entity.Category;
import com.tiki.server.team.entity.Team;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record TeamVO(
    @NotNull long teamId,
    @NotNull  String name,
    @NotNull  Category category,
    @NotNull  University univ,
    @NotNull String overview,
    @NotNull String imageUrl
) {
    public static TeamVO from(Team team) {
        return TeamVO.builder()
                .teamId(team.getId())
                .name(team.getName())
                .overview(team.getOverview())
                .category(team.getCategory())
                .univ(team.getUniv())
                .imageUrl(team.getImageUrl())
                .build();
    }
}
