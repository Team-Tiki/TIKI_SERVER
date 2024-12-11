package com.tiki.server.folder.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NonNull;

public record FolderNameUpdateRequest(
	@Schema(description = "수정할 폴더 이름", example = "수정할 폴더 1")
	@NonNull String name
) {
}