package com.tiki.server.team.dto.response;

import com.tiki.server.common.entity.University;
import com.tiki.server.team.entity.Category;
import com.tiki.server.team.entity.Team;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record TeamResponse(
        long teamId,
        String name,
        String overview,
        Category category,
        University univ,
        String imageUrl
) {
    public static TeamResponse from(Team team) {
        return TeamResponse.builder()
                .teamId(team.getId())
                .name(team.getName())
                .overview(team.getOverview())
                .category(team.getCategory())
                .univ(team.getUniv())
                .imageUrl(team.getImageUrl())
                .build();
    }

}
