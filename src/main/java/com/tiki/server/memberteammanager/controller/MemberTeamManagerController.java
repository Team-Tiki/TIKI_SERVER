package com.tiki.server.memberteammanager.controller;

import com.tiki.server.memberteammanager.controller.docs.MemberTeamManagerControllerDocs;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/members/teams")
public class MemberTeamManagerController implements MemberTeamManagerControllerDocs {
}
