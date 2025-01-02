package com.tiki.server.document.service.dto.response;

import com.tiki.server.document.entity.Document;

import jakarta.validation.constraints.NotNull;

public record DocumentTagGetServiceResponse(
	@NotNull long id,
	@NotNull String fileName,
	@NotNull String fileUrl,
	@NotNull double capacity
) {

	public static DocumentTagGetServiceResponse from(final Document document) {
		return new DocumentTagGetServiceResponse(
			document.getId(),
			document.getFileName(),
			document.getFileUrl(),
			document.getCapacity());
	}
}