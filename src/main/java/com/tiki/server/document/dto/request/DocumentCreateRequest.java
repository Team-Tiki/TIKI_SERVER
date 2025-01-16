package com.tiki.server.document.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record DocumentCreateRequest(
	@Schema(description = "파일 이름", example = "tiki.jpg")
	@NotNull String fileName,
	@Schema(description = "파일 url", example = "https://.../tiki.jpg")
	@NotNull String fileUrl,
	@Schema(description = "파일 key", example = "....jpg")
	@NotNull String fileKey,
	@Schema(description = "파일 용량 (단위 : byte)", example = "123")
	@NotNull long capacity
) {
}
