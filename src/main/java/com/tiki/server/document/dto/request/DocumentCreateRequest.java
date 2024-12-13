package com.tiki.server.document.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NonNull;

public record DocumentCreateRequest(
	@Schema(description = "파일 이름", example = "tiki.jpg")
	@NonNull String fileName,
	@Schema(description = "파일 url", example = "https://.../tiki.jpg")
	@NonNull String fileUrl,
	@Schema(description = "파일 key", example = "....jpg")
	@NonNull String fileKey,
	@Schema(description = "파일 용량", example = "1.23")
	double capacity
) {
}
