package com.tiki.server.team.service.dto.response;

import static lombok.AccessLevel.PRIVATE;

import com.tiki.server.common.entity.University;
import com.tiki.server.team.entity.Team;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder(access = PRIVATE)
public record TeamInformGetResponse(
	@NotNull String name,
	@NotNull University university,
	@NotNull String iconImageUrl,
	@NotNull LocalDate namingUpdatedAt
) {

	public static TeamInformGetResponse of(final Team team, final String iconImageUrl) {
		return TeamInformGetResponse.builder()
			.name(team.getName())
			.university(team.getUniv())
			.iconImageUrl(iconImageUrl)
			.namingUpdatedAt(team.getNamingUpdatedAt())
			.build();
	}
}
