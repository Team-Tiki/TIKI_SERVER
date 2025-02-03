package com.tiki.server.team.dto.request;

import com.tiki.server.common.util.Validator;
import jakarta.validation.constraints.NotNull;

public record TeamInformUpdateRequest(
        @NotNull String teamName,
        @NotNull String teamUrl
) {
    public TeamInformUpdateRequest(final String teamName, final String teamUrl) {
        Validator.validateLength(teamName, 30);
        this.teamName = teamName;
        this.teamUrl = teamUrl;
    }
}
