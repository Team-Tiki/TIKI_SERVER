package com.tiki.server.member.dto.response;

import com.tiki.server.memberteammanager.entity.MemberTeamManager;

import lombok.Builder;
import lombok.NonNull;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record BelongTeamGetResponse(
        long id,
        @NonNull String name,
        String iconImageUrl
) {
    public static BelongTeamGetResponse from(MemberTeamManager memberTeamManager) {
        return BelongTeamGetResponse.builder()
                .id(memberTeamManager.getTeam().getId())
                .name(memberTeamManager.getTeam().getName())
                .iconImageUrl(memberTeamManager.getTeam().getIconImageUrl())
                .build();
    }
}
