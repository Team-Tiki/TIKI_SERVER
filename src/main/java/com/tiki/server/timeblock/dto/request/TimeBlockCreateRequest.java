package com.tiki.server.timeblock.dto.request;

import java.time.LocalDate;
import java.util.List;

import com.tiki.server.timeblock.entity.BlockType;

import jakarta.validation.constraints.NotNull;

public record TimeBlockCreateRequest(
	@NotNull String name,
	@NotNull String color,
	@NotNull LocalDate startDate,
	@NotNull LocalDate endDate,
	@NotNull BlockType blockType,
	@NotNull List<Long> documentIds
) {
}
