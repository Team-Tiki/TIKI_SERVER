package com.tiki.server.team.dto.request;

import com.tiki.server.team.entity.Category;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

public record TeamCreateRequest(
	@NotNull String name,
	@NotNull Category category,
	@NotNull String iconImageUrl
) {
}
