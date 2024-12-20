package com.tiki.server.folder.dto.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record FolderNameUpdateRequest(
	@Schema(description = "수정할 폴더 이름", requiredMode = REQUIRED, example = "수정할 폴더 1")
	@NotNull String name
) {
}