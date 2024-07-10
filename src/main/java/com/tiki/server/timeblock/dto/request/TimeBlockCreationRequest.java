package com.tiki.server.timeblock.dto.request;

import java.time.LocalDate;
import java.util.List;

import lombok.NonNull;

public record TimeBlockCreationRequest(
	@NonNull String name,
	@NonNull String color,
	@NonNull LocalDate startDate,
	@NonNull LocalDate endDate,
	List<String> filesUrl
) {
}
