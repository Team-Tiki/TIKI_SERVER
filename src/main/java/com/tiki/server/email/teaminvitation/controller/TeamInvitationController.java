package com.tiki.server.email.teaminvitation.controller;

import static com.tiki.server.common.dto.SuccessResponse.success;
import static com.tiki.server.email.teaminvitation.messages.SuccessMessage.CREATE_INVITATION;

import com.tiki.server.common.dto.BaseResponse;
import com.tiki.server.email.teaminvitation.controller.dto.TeamInvitationCreateRequest;
import com.tiki.server.email.teaminvitation.service.TeamInvitationService;
import com.tiki.server.email.teaminvitation.service.dto.TeamInvitationCreateServiceRequest;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/team-invitation")
public class TeamInvitationController {

    private final TeamInvitationService teamInvitationService;

    @PostMapping("/{teamId}")
    public ResponseEntity<BaseResponse> createTeamInvitation(
            final Principal principal,
            @PathVariable long teamId,
            @RequestBody TeamInvitationCreateRequest request
    ) {
        long memberId = Long.parseLong(principal.getName());
        teamInvitationService.createTeamInvitation(
                TeamInvitationCreateServiceRequest.of(teamId, memberId, request.email()));
        return ResponseEntity.ok().body(success(CREATE_INVITATION.getMessage()));
    }
}
