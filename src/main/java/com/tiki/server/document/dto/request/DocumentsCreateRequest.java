package com.tiki.server.document.dto.request;

import java.util.List;

public record DocumentsCreateRequest(
	List<DocumentCreateRequest> documents
) {
}
