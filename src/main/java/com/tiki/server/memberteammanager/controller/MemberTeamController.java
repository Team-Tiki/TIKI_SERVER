package com.tiki.server.memberteammanager.controller;

import com.tiki.server.common.dto.BaseResponse;
import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.memberteammanager.controller.dto.request.UpdateTeamMemberNameRequest;
import com.tiki.server.memberteammanager.service.MemberTeamManagerService;
import com.tiki.server.memberteammanager.service.dto.response.MemberTeamInformGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static com.tiki.server.common.dto.SuccessResponse.success;
import static com.tiki.server.memberteammanager.message.SuccessMessage.GET_POSITION;
import static com.tiki.server.memberteammanager.message.SuccessMessage.KICK_TEAM;
import static com.tiki.server.memberteammanager.message.SuccessMessage.LEAVE_TEAM;
import static com.tiki.server.memberteammanager.message.SuccessMessage.UPDATE_NAME;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/team-member")
public class MemberTeamController {

    private final MemberTeamManagerService memberTeamManagerService;

    @GetMapping("/teams/{teamId}/members/position")
    public ResponseEntity<SuccessResponse<MemberTeamInformGetResponse>> getMemberTeamInform(
            final Principal principal,
            @PathVariable final long teamId
    ) {
        long memberId = Long.parseLong(principal.getName());
        MemberTeamInformGetResponse response = memberTeamManagerService.getMemberTeamInform(memberId, teamId);
        return ResponseEntity.ok().body(success(GET_POSITION.getMessage(), response));
    }

    @PatchMapping("/teams/{teamId}/members/name")
    public ResponseEntity<BaseResponse> updateTeamMemberName(
            final Principal principal,
            @PathVariable final long teamId,
            @RequestBody final UpdateTeamMemberNameRequest request
    ) {
        long memberId = Long.parseLong(principal.getName());
        memberTeamManagerService.updateTeamMemberName(memberId, teamId, request.newName());
        return ResponseEntity.ok(success(UPDATE_NAME.getMessage()));
    }

    @DeleteMapping("/teams/{teamId}/members/{kickOutMemberId}")
    public ResponseEntity<BaseResponse> kickOutMemberFromTeam(
            final Principal principal,
            @PathVariable final long teamId,
            @PathVariable final long kickOutMemberId
    ) {
        long memberId = Long.parseLong(principal.getName());
        memberTeamManagerService.kickOutMemberFromTeam(memberId, teamId, kickOutMemberId);
        return ResponseEntity.ok(success(KICK_TEAM.getMessage()));
    }

    @DeleteMapping("/teams/{teamId}/leave")
    public ResponseEntity<BaseResponse> leaveTeam(
            final Principal principal,
            @PathVariable final long teamId
    ) {
        long memberId = Long.parseLong(principal.getName());
        memberTeamManagerService.leaveTeam(memberId, teamId);
        return ResponseEntity.ok(success(LEAVE_TEAM.getMessage()));
    }
}
