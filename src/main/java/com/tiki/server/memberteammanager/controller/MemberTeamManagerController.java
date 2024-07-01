package com.tiki.server.memberteammanager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiki.server.memberteammanager.service.MemberTeamManagerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/members/teams")
public class MemberTeamManagerController {

	private final MemberTeamManagerService memberTeamManagerService;
}
