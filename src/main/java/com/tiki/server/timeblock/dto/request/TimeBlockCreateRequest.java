package com.tiki.server.timeblock.dto.request;

import java.time.LocalDate;
import java.util.Map;

import lombok.NonNull;

public record TimeBlockCreateRequest(
	@NonNull String name,
	@NonNull String color,
	@NonNull LocalDate startDate,
	@NonNull LocalDate endDate,
	Map<String, String> files
) {
}
