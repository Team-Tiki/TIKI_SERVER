package com.tiki.server.team.controller;

import static com.tiki.server.team.message.SuccessMessage.SUCCESS_ALTER_AUTHORITY;
import static com.tiki.server.team.message.SuccessMessage.SUCCESS_CREATE_TEAM;
import static com.tiki.server.team.message.SuccessMessage.SUCCESS_GET_CAPACITY_INFO;
import static com.tiki.server.team.message.SuccessMessage.SUCCESS_GET_CATEGORIES;
import static com.tiki.server.team.message.SuccessMessage.SUCCESS_GET_TEAMS;
import static com.tiki.server.team.message.SuccessMessage.SUCCESS_GET_TEAM_INFORM;
import static com.tiki.server.team.message.SuccessMessage.SUCCESS_UPDATE_TEAM_NAME;

import java.security.Principal;

import com.tiki.server.team.dto.request.TeamMemberAndTeamInformUpdateRequest;
import com.tiki.server.team.dto.request.TeamMemberAndTeamInformUpdateServiceRequest;
import com.tiki.server.team.dto.response.UsageGetResponse;
import com.tiki.server.team.dto.response.CategoriesGetResponse;
import com.tiki.server.team.dto.response.TeamsGetResponse;

import org.springframework.http.HttpStatus;
import com.tiki.server.team.service.dto.response.TeamInformGetResponse;
import org.springframework.web.bind.annotation.*;

import com.tiki.server.common.dto.SuccessResponse;
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
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public SuccessResponse<TeamCreateResponse> createTeam(
            final Principal principal,
            @RequestBody final TeamCreateRequest request
    ) {
        long memberId = Long.parseLong(principal.getName());
        TeamCreateResponse response = teamService.createTeam(memberId, request);
        return SuccessResponse.success(SUCCESS_CREATE_TEAM.getMessage(), response);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public SuccessResponse<TeamsGetResponse> getAllTeams(final Principal principal) {
        long memberId = Long.parseLong(principal.getName());
        TeamsGetResponse response = teamService.getAllTeams(memberId);
        return SuccessResponse.success(SUCCESS_GET_TEAMS.getMessage(), response);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/category")
    public SuccessResponse<CategoriesGetResponse> getCategories() {
        CategoriesGetResponse response = teamService.getCategories();
        return SuccessResponse.success(SUCCESS_GET_CATEGORIES.getMessage(), response);
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{teamId}")
    public void deleteTeam(
            final Principal principal,
            @PathVariable final long teamId
    ) {
        long memberId = Long.parseLong(principal.getName());
        teamService.deleteTeam(memberId, teamId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{teamId}/inform")
    public SuccessResponse<TeamInformGetResponse> getTeamName(
            @PathVariable final long teamId
    ) {
        TeamInformGetResponse response = teamService.getTeamInform(teamId);
        return SuccessResponse.success(SUCCESS_GET_TEAM_INFORM.getMessage(), response);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{teamId}/inform")
    public SuccessResponse<?> updateTeamAndTeamMemberInform(
            final Principal principal,
            @PathVariable final long teamId,
            @RequestBody final TeamMemberAndTeamInformUpdateRequest request
    ) {
        long memberId = Long.parseLong(principal.getName());
        teamService.updateTeamAndTeamMemberInform(memberId, teamId, TeamMemberAndTeamInformUpdateServiceRequest.from(request));
        return SuccessResponse.success(SUCCESS_UPDATE_TEAM_NAME.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{teamId}/member/{targetId}/admin")
    public SuccessResponse<?> alterAdmin(
            final Principal principal,
            @PathVariable final long teamId,
            @PathVariable final long targetId
    ) {
        long memberId = Long.parseLong(principal.getName());
        teamService.alterAdmin(memberId, teamId, targetId);
        return SuccessResponse.success(SUCCESS_ALTER_AUTHORITY.getMessage());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{teamId}/capacity")
    public SuccessResponse<UsageGetResponse> getCapacityInfo(
        final Principal principal,
        @PathVariable final long teamId
    ) {
        long memberId = Long.parseLong(principal.getName());
        UsageGetResponse response = teamService.getCapacityInfo(memberId, teamId);
        return SuccessResponse.success(SUCCESS_GET_CAPACITY_INFO.getMessage(), response);
    }
}
