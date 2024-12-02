package com.tiki.server.folder.dto.request;

import lombok.NonNull;

public record FolderNameUpdateRequest(
	@NonNull String name
) {
}