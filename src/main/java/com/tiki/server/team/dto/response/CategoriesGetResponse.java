package com.tiki.server.team.dto.response;

import static lombok.AccessLevel.PRIVATE;

import java.util.Arrays;
import java.util.List;

import com.tiki.server.team.entity.Category;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(access = PRIVATE)
public record CategoriesGetResponse(
	@NotNull List<Category> categories
) {

	public static CategoriesGetResponse from(final Category[] categories) {
		return CategoriesGetResponse.builder()
			.categories(Arrays.stream(categories).toList())
			.build();
	}
}
