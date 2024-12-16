package com.tiki.server.team.dto.response;

import com.tiki.server.common.entity.University;
import com.tiki.server.team.entity.Category;
import com.tiki.server.team.entity.Team;
import com.tiki.server.team.vo.TeamVO;

import lombok.Builder;
import lombok.NonNull;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record TeamGetResponse(
	long teamId,
	@NonNull String name,
	@NonNull Category category,
	@NonNull University univ,
	String overview,
	String imageUrl
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
