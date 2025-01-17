package com.tiki.server.team.dto.response;

import static lombok.AccessLevel.PRIVATE;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(access = PRIVATE)
public record UsageGetResponse(
	@NotNull long capacity,
	@NotNull long usage
) {

	public static UsageGetResponse of(final long capacity, final long usage) {
		return UsageGetResponse.builder()
			.capacity(capacity)
			.usage(usage)
			.build();
	}
}
