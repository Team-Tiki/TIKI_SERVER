package com.tiki.server.timeblock.dto.request;

import com.tiki.server.common.util.Validator;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record TimeBlockUpdateRequest(
	@NotNull String name,
	@NotNull LocalDate startDate,
	@NotNull LocalDate endDate
) {
	public TimeBlockUpdateRequest(final String name, final LocalDate startDate, final LocalDate endDate) {
		Validator.validateLengthContainEmoji(name, 25);
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}
}
