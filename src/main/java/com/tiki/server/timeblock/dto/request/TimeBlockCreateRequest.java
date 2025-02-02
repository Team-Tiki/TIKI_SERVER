package com.tiki.server.timeblock.dto.request;

import com.tiki.server.common.util.Validator;
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
	public TimeBlockCreateRequest(final String name, final String color, final LocalDate startDate, final LocalDate endDate, final BlockType blockType, final List<Long> documentIds) {
		Validator.validateLength(name, 25);
		this.name = name;
		this.color = color;
		this.startDate = startDate;
		this.endDate = endDate;
		this.blockType = blockType;
		this.documentIds = documentIds;
	}
}
