package com.tiki.server.memberteammanager.controller;

import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.memberteammanager.dto.response.BelongTeamsResponse;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiki.server.memberteammanager.service.MemberTeamManagerService;

import lombok.RequiredArgsConstructor;

import java.security.Principal;
import java.util.List;

import static com.tiki.server.common.dto.SuccessResponse.success;
import static com.tiki.server.team.message.SuccessMessage.SUCCESS_GET_JOINED_TEAM;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/members/teams")
public class MemberTeamManagerController {

    private final MemberTeamManagerService memberTeamManagerService;

    @GetMapping("/joined")
    public ResponseEntity<SuccessResponse<List<BelongTeamsResponse>>> showBelongTeam(
            Principal principal
    ) {
        val memberId = Long.parseLong(principal.getName());
        val response = memberTeamManagerService.findBelongTeams(memberId);
        return ResponseEntity.ok().body(success(SUCCESS_GET_JOINED_TEAM.getMessage(), response));
    }
}
