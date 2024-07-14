package com.tiki.server.external.dto.request;

import lombok.NonNull;

public record S3DeleteRequest(
	@NonNull String fileName
) {
}
