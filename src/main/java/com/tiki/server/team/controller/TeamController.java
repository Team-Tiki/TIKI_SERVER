package com.tiki.server.team.controller;

import static com.tiki.server.team.message.SuccessMessage.SUCCESS_CREATE_TEAM;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiki.server.common.dto.BaseResponse;
import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.common.support.UriGenerator;
import com.tiki.server.team.controller.docs.TeamControllerDocs;
import com.tiki.server.team.dto.request.TeamCreateRequest;
import com.tiki.server.team.dto.response.TeamCreateResponse;
import com.tiki.server.team.service.TeamService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/teams")
public class TeamController implements TeamControllerDocs {

	private final TeamService teamService;

	@Override
	@PostMapping
	public ResponseEntity<SuccessResponse<TeamCreateResponse>> createTeam(
		Principal principal,
		@RequestBody TeamCreateRequest request
	) {
		val memberId = Long.parseLong(principal.getName());
		val response = teamService.createTeam(memberId, request);
		return ResponseEntity.created(
			UriGenerator.getUri("/api/v1/teams/" + response.teamId())
		).body(SuccessResponse.success(SUCCESS_CREATE_TEAM.getMessage(), response));
	}
}
