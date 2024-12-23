package com.tiki.server.member.dto.response;

import com.tiki.server.memberteammanager.entity.MemberTeamManager;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record BelongTeamsGetResponse(
        @NotNull List<BelongTeamGetResponse> belongTeamGetResponses
) {

    public static BelongTeamsGetResponse from(final List<MemberTeamManager> belongTeamGetResponses) {
        return BelongTeamsGetResponse.builder()
                .belongTeamGetResponses(belongTeamGetResponses.stream().map(BelongTeamGetResponse::from).toList())
                .build();
    }

    @Builder(access = PRIVATE)
    public record BelongTeamGetResponse(
            @NotNull long id,
            @NotNull String name,
            @NotNull String iconImageUrl
    ) {
        public static BelongTeamGetResponse from(final MemberTeamManager memberTeamManager) {
            return BelongTeamGetResponse.builder()
                    .id(memberTeamManager.getTeam().getId())
                    .name(memberTeamManager.getTeam().getName())
                    .iconImageUrl(memberTeamManager.getTeam().getIconImageUrl())
                    .build();
        }
    }
}
