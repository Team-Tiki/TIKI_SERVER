package com.tiki.server.external.dto.request;

import lombok.NonNull;

public record PreSignedUrlRequest(
	@NonNull String fileFormat
) {
}
