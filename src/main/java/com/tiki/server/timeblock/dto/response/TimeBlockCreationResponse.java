package com.tiki.server.timeblock.dto.response;

import static lombok.AccessLevel.PRIVATE;

import lombok.Builder;

@Builder(access = PRIVATE)
public record TimeBlockCreationResponse(
	long timeBlockId
) {

	public static TimeBlockCreationResponse of(long timeBlockId) {
		return TimeBlockCreationResponse.builder()
			.timeBlockId(timeBlockId)
			.build();
	}
}
