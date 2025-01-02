package com.tiki.server.team.dto.response;

import com.tiki.server.team.entity.Team;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record TeamsGetResponse(
	@NotNull List<TeamGetResponse> teams
) {
	public static TeamsGetResponse from(final List<Team> teams) {
		return TeamsGetResponse.builder()
			.teams(teams.stream().map(TeamGetResponse::from).toList())
			.build();
	}
}
