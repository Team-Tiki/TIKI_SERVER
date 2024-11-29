package com.tiki.server.memberteammanager.controller;

import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.memberteammanager.controller.dto.request.UpdateTeamMemberNameRequest;
import com.tiki.server.memberteammanager.service.MemberTeamManagerService;
import com.tiki.server.memberteammanager.service.dto.response.MemberTeamPositionGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static com.tiki.server.common.dto.SuccessResponse.success;
import static com.tiki.server.memberteammanager.message.SuccessMessage.GET_POSITION;
import static com.tiki.server.memberteammanager.message.SuccessMessage.UPDATE_NAME;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/team-member")
public class MemberTeamController {

    private final MemberTeamManagerService memberTeamManagerService;

    @GetMapping("/teams/{teamId}/members/position")
    public ResponseEntity<SuccessResponse<MemberTeamPositionGetResponse>> getMemberTeamPosition(
            Principal principal,
            @PathVariable final long teamId
    ) {
        long memberId = Long.parseLong(principal.getName());
        MemberTeamPositionGetResponse response = memberTeamManagerService.getPosition(memberId, teamId);
        return ResponseEntity.ok().body(success(GET_POSITION.getMessage(), response));
    }

    @PatchMapping("/teams/{teamId}/members/name")
    public ResponseEntity<SuccessResponse<Void>> updateTeamMemberName(
            Principal principal,
            @PathVariable final long teamId,
            @RequestBody final UpdateTeamMemberNameRequest request
    ) {
        long memberId = Long.parseLong(principal.getName());
        memberTeamManagerService.updateTeamMemberName(memberId, teamId, request.newName());
        return ResponseEntity.ok().body(success(UPDATE_NAME.getMessage(), null));
    }
}
