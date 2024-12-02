package com.tiki.server.memberteammanager.controller;

import com.tiki.server.common.dto.BaseResponse;
import com.tiki.server.memberteammanager.service.MemberTeamManagerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
@AllArgsConstructor
@RequestMapping("api/v1/teams")
public class MemberTeamManagerController {

    private final MemberTeamManagerService memberTeamManagerService;

    @DeleteMapping("/{teamId}/members/{kickOutMemberId}")
    public ResponseEntity<BaseResponse> kickOutMemberFromTeam(
            final Principal principal,
            @PathVariable final long teamId,
            @PathVariable final long kickOutMemberId
    ) {
        long memberId = Long.parseLong(principal.getName());
        memberTeamManagerService.kickOutMemberFromTeam(memberId, teamId, kickOutMemberId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{teamId}/leave")
    public ResponseEntity<BaseResponse> leaveTeam(
            final Principal principal,
            @PathVariable final long teamId
    ) {
        long memberId = Long.parseLong(principal.getName());
        memberTeamManagerService.leaveTeam(memberId, teamId);
        return ResponseEntity.noContent().build();
    }
}
