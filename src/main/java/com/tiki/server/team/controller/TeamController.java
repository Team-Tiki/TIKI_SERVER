package com.tiki.server.team.controller;

import static com.tiki.server.common.dto.SuccessResponse.success;
import static com.tiki.server.team.message.SuccessMessage.*;

import java.security.Principal;

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
        ).body(success(SUCCESS_CREATE_TEAM.getMessage(), response));
    }

    @Override
    @GetMapping
    public ResponseEntity<SuccessResponse<TeamsGetResponse>> showAllTeam(Principal principal) {
        val memberId = Long.parseLong(principal.getName());
        val response = teamService.showAllTeam(memberId);
        return ResponseEntity.ok().body(success(SUCCESS_GET_TEAMS.getMessage(), response));
    }

    @Override
    @GetMapping("/category")
    public ResponseEntity<SuccessResponse<CategoriesGetResponse>> getCategories() {
        val response = teamService.getCategories();
        return ResponseEntity.ok().body(success(SUCCESS_GET_CATEGORIES.getMessage(), response));
    }
}
