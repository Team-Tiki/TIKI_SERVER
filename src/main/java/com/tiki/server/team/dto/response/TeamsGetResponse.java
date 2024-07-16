package com.tiki.server.team.dto.response;

import com.tiki.server.team.vo.TeamVO;
import lombok.Builder;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record TeamsGetResponse(
        List<TeamGetResponse> teams
) {
    public static TeamsGetResponse from(List<TeamVO> teams) {
        return TeamsGetResponse.builder()
                .teams(teams.stream().map(TeamGetResponse::from).toList())
                .build();
    }
}
