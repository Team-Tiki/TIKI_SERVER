package com.tiki.server.team.controller;

import static com.tiki.server.common.dto.SuccessResponse.success;
import static com.tiki.server.team.message.SuccessMessage.*;

import java.security.Principal;

import com.tiki.server.common.dto.BaseResponse;
import com.tiki.server.team.dto.response.CategoriesGetResponse;
import com.tiki.server.team.dto.response.TeamsGetResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.common.support.UriGenerator;
import com.tiki.server.team.controller.docs.TeamControllerDocs;
import com.tiki.server.team.dto.request.TeamCreateRequest;
import com.tiki.server.team.dto.response.TeamCreateResponse;
import com.tiki.server.team.service.TeamService;

import lombok.RequiredArgsConstructor;

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
		long memberId = Long.parseLong(principal.getName());
		TeamCreateResponse response = teamService.createTeam(memberId, request);
		return ResponseEntity.created(
			UriGenerator.getUri("/api/v1/teams/" + response.teamId())
		).body(success(SUCCESS_CREATE_TEAM.getMessage(), response));
	}

	@Override
	@GetMapping
	public ResponseEntity<SuccessResponse<TeamsGetResponse>> getAllTeams(Principal principal) {
		long memberId = Long.parseLong(principal.getName());
		TeamsGetResponse response = teamService.getAllTeams(memberId);
		return ResponseEntity.ok().body(success(SUCCESS_GET_TEAMS.getMessage(), response));
	}

	@Override
	@GetMapping("/category")
	public ResponseEntity<SuccessResponse<CategoriesGetResponse>> getCategories() {
		CategoriesGetResponse response = teamService.getCategories();
		return ResponseEntity.ok().body(success(SUCCESS_GET_CATEGORIES.getMessage(), response));
	}

	@DeleteMapping("/{teamId}/members")
	public ResponseEntity<BaseResponse> kickOutMemberFromTeam(
			Principal principal,
			@PathVariable long teamId,
			@RequestParam long kickOutMemberId
	){
		long memberId = Long.parseLong(principal.getName());
		teamService.kickOutMemberFromTeam(memberId, teamId, kickOutMemberId);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{teamId}/leave")
	public ResponseEntity<BaseResponse> leaveTeam(
			Principal principal,
			@PathVariable long teamId
	){
		long memberId = Long.parseLong(principal.getName());
		teamService.leaveTeam(memberId, teamId);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{teamId}")
	public ResponseEntity<BaseResponse> deleteTeam(
		Principal principal,
		@PathVariable long teamId
	) {
		long memberId = Long.parseLong(principal.getName());
		teamService.deleteTeam(memberId, teamId);
		return ResponseEntity.noContent().build();
	}
}
