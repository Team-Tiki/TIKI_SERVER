package com.tiki.server.folder.dto.request;

import lombok.NonNull;

public record FolderCreateRequest(
	@NonNull String name
) {
}
