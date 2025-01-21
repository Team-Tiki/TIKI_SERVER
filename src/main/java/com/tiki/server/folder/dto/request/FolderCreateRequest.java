package com.tiki.server.folder.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record FolderCreateRequest(
	@Schema(description = "폴더 이름", example = "폴더 1")
	@NotNull String name
) {
}
