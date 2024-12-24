package com.tiki.server.memberteammanager.controller;

import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.memberteammanager.controller.dto.request.UpdateTeamMemberNameRequest;
import com.tiki.server.memberteammanager.service.MemberTeamManagerService;
import com.tiki.server.memberteammanager.service.dto.response.MemberTeamInformGetResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static com.tiki.server.memberteammanager.message.SuccessMessage.GET_TEAM_INFORM;
import static com.tiki.server.memberteammanager.message.SuccessMessage.KICK_TEAM;
import static com.tiki.server.memberteammanager.message.SuccessMessage.LEAVE_TEAM;
import static com.tiki.server.memberteammanager.message.SuccessMessage.UPDATE_NAME;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/team-member")
public class MemberTeamController {

    private final MemberTeamManagerService memberTeamManagerService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/teams/{teamId}/members/position")
    public SuccessResponse<MemberTeamInformGetResponse> getMemberTeamInform(
            final Principal principal,
            @PathVariable final long teamId
    ) {
        long memberId = Long.parseLong(principal.getName());
        MemberTeamInformGetResponse response = memberTeamManagerService.getMemberTeamInform(memberId, teamId);
        return SuccessResponse.success(GET_TEAM_INFORM.getMessage(), response);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/teams/{teamId}/members/name")
    public SuccessResponse<?> updateTeamMemberName(
            final Principal principal,
            @PathVariable final long teamId,
            @RequestBody final UpdateTeamMemberNameRequest request
    ) {
        long memberId = Long.parseLong(principal.getName());
        memberTeamManagerService.updateTeamMemberName(memberId, teamId, request.newName());
        return SuccessResponse.success(UPDATE_NAME.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/teams/{teamId}/members/{kickOutMemberId}")
    public SuccessResponse<?> kickOutMemberFromTeam(
            final Principal principal,
            @PathVariable final long teamId,
            @PathVariable final long kickOutMemberId
    ) {
        long memberId = Long.parseLong(principal.getName());
        memberTeamManagerService.kickOutMemberFromTeam(memberId, teamId, kickOutMemberId);
        return SuccessResponse.success(KICK_TEAM.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/teams/{teamId}/leave")
    public SuccessResponse<?> leaveTeam(
            final Principal principal,
            @PathVariable final long teamId
    ) {
        long memberId = Long.parseLong(principal.getName());
        memberTeamManagerService.leaveTeam(memberId, teamId);
        return SuccessResponse.success(LEAVE_TEAM.getMessage());
    }
}
