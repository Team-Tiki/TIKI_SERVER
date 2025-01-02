package com.tiki.server.email.teaminvitation.controller;

import static com.tiki.server.email.teaminvitation.messages.SuccessMessage.*;

import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.email.teaminvitation.service.TeamInvitationService;
import com.tiki.server.email.teaminvitation.service.dto.TeamInvitationEmailsGetResponse;
import com.tiki.server.email.teaminvitation.service.dto.TeamInvitationInformGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/team-invitation")
public class TeamInvitationController {

    private final TeamInvitationService teamInvitationService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/team/{teamId}")
    public SuccessResponse<TeamInvitationEmailsGetResponse> getTeamInvitation(
            Principal principal,
            @PathVariable long teamId
    ) {
        long memberId = Long.parseLong(principal.getName());
        TeamInvitationEmailsGetResponse response = teamInvitationService.getInvitations(memberId, teamId);
        return SuccessResponse.success(GET_TEAM_INVITATIONS.getMessage(), response);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/team/{teamId}")
    public SuccessResponse<?> deleteTeamInvitationFromAdmin(
            Principal principal,
            @RequestParam long invitationId,
            @PathVariable long teamId
    ) {
        long memberId = Long.parseLong(principal.getName());
        teamInvitationService.deleteTeamInvitationFromAdmin(memberId, teamId, invitationId);
        return SuccessResponse.success(DELETE_TEAM_INVITATION_FROM_ADMIN.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public SuccessResponse<TeamInvitationInformGetResponse> getInvitationInform(
            @RequestParam final long invitationId
    ) {
        TeamInvitationInformGetResponse response = teamInvitationService.getInvitationInform(invitationId);
        return SuccessResponse.success(GET_TEAM_INVITATION_INFORM.getMessage(), response);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public SuccessResponse<?> createTeamMemberFromInvitation(
            Principal principal,
            @RequestParam final long teamId,
            @RequestParam final long teamInvitationId
    ) {
        long memberId = Long.parseLong(principal.getName());
        teamInvitationService.createTeamMemberFromInvitation(memberId, teamId, teamInvitationId);
        return SuccessResponse.success(CREATE_TEAM_MEMBER_FROM_INVITATION.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    public SuccessResponse<?> deleteTeamInvitationFromUser(
            Principal principal,
            @RequestParam final long invitationId
    ) {
        long memberId = Long.parseLong(principal.getName());
        teamInvitationService.deleteTeamInvitation(memberId, invitationId);
        return SuccessResponse.success(DELETE_TEAM_INVITATION_FROM_USER.getMessage());
    }
}
