package com.tiki.server.document.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public record DocumentsCreateRequest(
	@NotNull List<DocumentCreateRequest> documents
) {
}
