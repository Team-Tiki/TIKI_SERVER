package com.tiki.server.memberteammanager.dto.response;

import com.tiki.server.memberteammanager.entity.MemberTeamManager;

import lombok.Builder;
import lombok.NonNull;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record BelongTeamsResponse(
        long id,
        @NonNull String name,
        String iconImageUrl
) {
    public static BelongTeamsResponse from(MemberTeamManager memberTeamManager) {
        return BelongTeamsResponse.builder()
                .id(memberTeamManager.getTeam().getId())
                .name(memberTeamManager.getTeam().getName())
                .iconImageUrl(memberTeamManager.getTeam().getImageUrl())
                .build();
    }
}
