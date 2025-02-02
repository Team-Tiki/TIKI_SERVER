package com.tiki.server.team.dto.request;

import com.tiki.server.common.util.Validator;
import com.tiki.server.team.entity.Category;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

public record TeamCreateRequest(
	@NotNull String name,
	@NotNull Category category,
	@NotNull String iconImageUrl
) {
	public TeamCreateRequest(final String name, final Category category, final String iconImageUrl) {
		Validator.validateLength(name, 30);
		this.name = name;
		this.category = category;
		this.iconImageUrl = iconImageUrl;
	}
}
