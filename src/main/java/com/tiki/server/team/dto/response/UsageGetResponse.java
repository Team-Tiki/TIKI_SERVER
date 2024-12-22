package com.tiki.server.team.dto.response;

import static lombok.AccessLevel.PRIVATE;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(access = PRIVATE)
public record UsageGetResponse(
	@NotNull double capacity,
	@NotNull double usage
) {

	public static UsageGetResponse of(final double capacity, final double usage) {
		return UsageGetResponse.builder()
			.capacity(capacity)
			.usage(usage)
			.build();
	}
}
