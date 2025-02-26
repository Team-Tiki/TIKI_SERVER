package com.tiki.server.team.dto.response;

import static lombok.AccessLevel.PRIVATE;

import com.tiki.server.common.entity.University;
import com.tiki.server.team.entity.Category;
import com.tiki.server.team.entity.Team;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(access = PRIVATE)
public record TeamResponse(
	@NotNull long teamId,
	@NotNull String name,
	@NotNull Category category,
	@NotNull University univ,
	@NotNull String overview,
	@NotNull String imageUrl,
	@NotNull String iconImageUrl
) {

	public static TeamResponse createWithImage(final Team team, final String url) {
		return TeamResponse.builder()
			.teamId(team.getId())
			.name(team.getName())
			.overview(team.getOverview())
			.category(team.getCategory())
			.univ(team.getUniv())
			.imageUrl(url)
			.iconImageUrl(null)
			.build();
	}

	public static TeamResponse createWithIcon(final Team team, final String url) {
		return TeamResponse.builder()
			.teamId(team.getId())
			.name(team.getName())
			.overview(team.getOverview())
			.category(team.getCategory())
			.univ(team.getUniv())
			.imageUrl(null)
			.iconImageUrl(url)
			.build();
	}
}
