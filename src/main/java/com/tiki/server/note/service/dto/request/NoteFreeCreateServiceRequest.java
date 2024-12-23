package com.tiki.server.note.service.dto.request;

import com.tiki.server.note.controller.dto.request.NoteFreeCreateRequest;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record NoteFreeCreateServiceRequest(
	@NotNull String title,
	@NotNull boolean complete,
	@NotNull LocalDate startDate,
	@NotNull LocalDate endDate,
	@NotNull String contents,
	@NotNull long teamId,
	@NotNull List<Long> timeBlockIds,
	@NotNull List<Long> documentIds,
	@NotNull long memberId
) {
	public static NoteFreeCreateServiceRequest of(
		final NoteFreeCreateRequest request,
		final long memberId
	) {
		return new NoteFreeCreateServiceRequest(
			request.title(),
			request.complete(),
			request.startDate(),
			request.endDate(),
			request.contents(),
			request.teamId(),
			request.timeBlockIds(),
			request.documentIds(),
			memberId
		);
	}
}
