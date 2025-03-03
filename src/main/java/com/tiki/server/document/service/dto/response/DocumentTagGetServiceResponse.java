package com.tiki.server.document.service.dto.response;

import com.tiki.server.document.dto.response.DocumentResponse;

import jakarta.validation.constraints.NotNull;

public record DocumentTagGetServiceResponse(
	@NotNull long id,
	@NotNull String fileName,
	@NotNull String fileUrl,
	@NotNull long capacity
) {

	public static DocumentTagGetServiceResponse from(final DocumentResponse document) {
		return new DocumentTagGetServiceResponse(
			document.documentId(),
			document.name(),
			document.url(),
			document.capacity());
	}
}