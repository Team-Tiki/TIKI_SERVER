package com.tiki.server.team.dto.response;

import static lombok.AccessLevel.PRIVATE;

import com.tiki.server.team.entity.Team;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(access = PRIVATE)
public record TeamCreateResponse(
	@NotNull long teamId
) {

	public static TeamCreateResponse from(final Team team) {
		return TeamCreateResponse.builder()
			.teamId(team.getId())
			.build();
	}
}
