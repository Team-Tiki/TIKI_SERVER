package com.tiki.server.document.dto.response;

import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDateTime;

import com.tiki.server.document.entity.Document;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(access = PRIVATE)
public record DocumentResponse(
	@NotNull long documentId,
	@NotNull String name,
	@NotNull String url,
	@NotNull long capacity,
	@NotNull LocalDateTime createdTime
) {

	public static DocumentResponse of(final Document document, final String url) {
		return DocumentResponse.builder()
			.documentId(document.getId())
			.name(document.getFileName())
			.url(url)
			.capacity(document.getCapacity())
			.createdTime(document.getCreatedAt())
			.build();
	}
}
