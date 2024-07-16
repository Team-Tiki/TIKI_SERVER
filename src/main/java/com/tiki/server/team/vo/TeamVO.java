package com.tiki.server.team.vo;

import com.tiki.server.common.entity.University;
import com.tiki.server.team.entity.Category;
import com.tiki.server.team.entity.Team;
import lombok.Builder;
import lombok.NonNull;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record TeamVO(
        long teamId,
        @NonNull String name,
        @NonNull Category category,
        @NonNull University univ,
        String overview,
        String imageUrl
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
