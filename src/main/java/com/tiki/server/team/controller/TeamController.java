package com.tiki.server.team.controller;

import static com.tiki.server.common.dto.SuccessResponse.success;
import static com.tiki.server.team.message.SuccessMessage.*;

import java.security.Principal;

import com.tiki.server.common.dto.BaseResponse;
import com.tiki.server.team.controller.dto.request.UpdateTeamIconRequest;
import com.tiki.server.team.controller.dto.request.UpdateTeamNameRequest;
import com.tiki.server.team.controller.dto.response.UsageGetResponse;
import com.tiki.server.team.dto.response.CategoriesGetResponse;
import com.tiki.server.team.dto.response.TeamsGetResponse;

import org.springframework.http.HttpStatus;
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
            final Principal principal,
            @RequestBody final TeamCreateRequest request
    ) {
        long memberId = Long.parseLong(principal.getName());
        TeamCreateResponse response = teamService.createTeam(memberId, request);
        return ResponseEntity.created(
                UriGenerator.getUri("/api/v1/teams/" + response.teamId())
        ).body(success(SUCCESS_CREATE_TEAM.getMessage(), response));
    }

    @Override
    @GetMapping
    public ResponseEntity<SuccessResponse<TeamsGetResponse>> getAllTeams(final Principal principal) {
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

    @DeleteMapping("/{teamId}")
    public ResponseEntity<BaseResponse> deleteTeam(
            final Principal principal,
            @PathVariable final long teamId
    ) {
        long memberId = Long.parseLong(principal.getName());
        teamService.deleteTeam(memberId, teamId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{teamId}/name")
    public ResponseEntity<BaseResponse> updateTeamName(
            final Principal principal,
            @PathVariable final long teamId,
            @RequestBody final UpdateTeamNameRequest request
    ) {
        long memberId = Long.parseLong(principal.getName());
        teamService.updateTeamName(memberId, teamId, request.newTeamName());
        return ResponseEntity.ok(success(SUCCESS_UPDATE_TEAM_NAME.getMessage()));
    }

    @PatchMapping("/{teamId}/icon")
    public ResponseEntity<BaseResponse> updateIconImage(
            final Principal principal,
            @PathVariable final long teamId,
            @RequestBody final UpdateTeamIconRequest request
    ) {
        long memberId = Long.parseLong(principal.getName());
        teamService.updateIconImage(memberId, teamId, request.iconImageUrl());
        return ResponseEntity.ok(success(SUCCESS_UPDATE_TEAM_ICON.getMessage()));
    }

    @PatchMapping("/{teamId}/member/{targetId}/admin")
    public ResponseEntity<BaseResponse> alterAdmin(
            final Principal principal,
            @PathVariable final long teamId,
            @PathVariable final long targetId
    ) {
        long memberId = Long.parseLong(principal.getName());
        teamService.alterAdmin(memberId, teamId, targetId);
        return ResponseEntity.ok(success(SUCCESS_ALTER_AUTHORITY.getMessage()));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{teamId}/capacity")
    public SuccessResponse<UsageGetResponse> getCapacityInfo(final Principal principal, @PathVariable final long teamId) {
        long memberId = Long.parseLong(principal.getName());
        UsageGetResponse response = teamService.getCapacityInfo(memberId, teamId);
        return success(SUCCESS_GET_CAPACITY_INFO.getMessage(), response);
    }
}
