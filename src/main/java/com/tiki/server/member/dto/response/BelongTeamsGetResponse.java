package com.tiki.server.member.dto.response;

import com.tiki.server.memberteammanager.entity.MemberTeamManager;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record BelongTeamsGetResponse(
        @NonNull List<BelongTeamGetResponse> belongTeamGetResponses
) {

    public static BelongTeamsGetResponse from(List<MemberTeamManager> belongTeamGetResponses) {
        return BelongTeamsGetResponse.builder()
                .belongTeamGetResponses(belongTeamGetResponses.stream().map(BelongTeamGetResponse::from).toList())
                .build();
    }

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
}
