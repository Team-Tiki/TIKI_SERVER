package com.tiki.server.email.teaminvitation.controller;

import static com.tiki.server.common.dto.SuccessResponse.success;
import static com.tiki.server.email.teaminvitation.messages.SuccessMessage.*;

import com.tiki.server.common.dto.BaseResponse;
import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.email.teaminvitation.service.TeamInvitationService;
import com.tiki.server.email.teaminvitation.service.dto.TeamInvitationEmailsGetResponse;
import com.tiki.server.email.teaminvitation.service.dto.TeamInvitationInformGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/team-invitation")
public class TeamInvitationController {

    private final TeamInvitationService teamInvitationService;

    @GetMapping("/team/{teamId}")
    public ResponseEntity<SuccessResponse<TeamInvitationEmailsGetResponse>> getTeamInvitation(
            Principal principal,
            @PathVariable long teamId
    ) {
        long memberId = Long.parseLong(principal.getName());
        TeamInvitationEmailsGetResponse response = teamInvitationService.getInvitations(memberId, teamId);
        return ResponseEntity.ok(success(GET_TEAM_INVITATIONS.getMessage(), response));
    }

    @DeleteMapping("/team/{teamId}")
    public ResponseEntity<BaseResponse> deleteTeamInvitationFromAdmin(
            Principal principal,
            @RequestParam long invitationId,
            @PathVariable long teamId
    ) {
        long memberId = Long.parseLong(principal.getName());
        teamInvitationService.deleteTeamInvitationFromAdmin(memberId, teamId, invitationId);
        return ResponseEntity.ok().body(success(DELETE_TEAM_INVITATION_FROM_ADMIN.getMessage()));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<TeamInvitationInformGetResponse>> getInvitationInform(
            @RequestParam final long invitationId
    ) {
        TeamInvitationInformGetResponse response = teamInvitationService.getInvitationInform(invitationId);
        return ResponseEntity.ok(success(GET_TEAM_INVITATION_INFORM.getMessage(), response));
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createTeamMemberFromInvitation(
            Principal principal,
            @RequestParam final long teamId,
            @RequestParam final long teamInvitationId
    ) {
        long memberId = Long.parseLong(principal.getName());
        teamInvitationService.createTeamMemberFromInvitation(memberId, teamId, teamInvitationId);
        return ResponseEntity.ok(success(CREATE_TEAM_MEMBER_FROM_INVITATION.getMessage()));
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse> deleteTeamInvitationFromUser(
            Principal principal,
            @RequestParam final long invitationId
    ) {
        long memberId = Long.parseLong(principal.getName());
        teamInvitationService.deleteTeamInvitation(memberId, invitationId);
        return ResponseEntity.ok(success(DELETE_TEAM_INVITATION_FROM_USER.getMessage()));
    }
}
