package com.tiki.server.memberteammanager.controller;

import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.common.support.UriGenerator;
import com.tiki.server.memberteammanager.dto.response.BelongTeamsResponse;
import com.tiki.server.team.dto.response.TeamCreateResponse;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiki.server.memberteammanager.service.MemberTeamManagerService;

import lombok.RequiredArgsConstructor;

import java.security.Principal;

import static com.tiki.server.team.message.SuccessMessage.SUCCESS_CREATE_TEAM;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/members/teams")
public class MemberTeamManagerController {

    private final MemberTeamManagerService memberTeamManagerService;
}
