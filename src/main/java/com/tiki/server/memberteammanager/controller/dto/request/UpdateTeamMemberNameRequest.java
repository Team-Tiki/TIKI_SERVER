package com.tiki.server.memberteammanager.controller.dto.request;

import com.tiki.server.common.util.Validator;
import jakarta.validation.constraints.NotNull;

public record UpdateTeamMemberNameRequest(
        @NotNull String newName
) {

    public UpdateTeamMemberNameRequest(final String newName) {
        Validator.validateLength(newName, 32);
        this.newName = newName;
    }
}
