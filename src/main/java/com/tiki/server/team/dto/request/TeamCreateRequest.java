package com.tiki.server.team.dto.request;

import com.tiki.server.team.entity.Category;

import lombok.NonNull;

public record TeamCreateRequest(
        @NonNull String name,
        @NonNull Category category,
        @NonNull String iconImageUrl
) {
}
