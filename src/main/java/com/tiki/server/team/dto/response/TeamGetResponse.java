package com.tiki.server.team.dto.response;

import com.tiki.server.common.entity.University;
import com.tiki.server.team.entity.Category;
import com.tiki.server.team.vo.TeamVO;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record TeamGetResponse(
	@NotNull long teamId,
	@NotNull String name,
	@NotNull Category category,
	@NotNull University univ,
	@NotNull String overview,
	@NotNull String imageUrl
) {
	public static TeamGetResponse from(TeamVO team) {
		return TeamGetResponse.builder()
			.teamId(team.teamId())
			.name(team.name())
			.overview(team.overview())
			.category(team.category())
			.univ(team.univ())
			.imageUrl(team.imageUrl())
			.build();
	}

}
