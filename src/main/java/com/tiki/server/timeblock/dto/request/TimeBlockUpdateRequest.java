package com.tiki.server.timeblock.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record TimeBlockUpdateRequest(
	@NotNull String name,
	@NotNull LocalDate startDate,
	@NotNull LocalDate endDate
) {
}
