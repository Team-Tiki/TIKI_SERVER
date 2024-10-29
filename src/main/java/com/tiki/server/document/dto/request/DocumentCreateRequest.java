package com.tiki.server.document.dto.request;

import lombok.NonNull;

public record DocumentCreateRequest(
	@NonNull String fileName,
	@NonNull String fileUrl,
	double capacity
) {
}
