package com.tiki.server.teaminvitation.controller;

import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.teaminvitation.service.TeamInvitationService;
import com.tiki.server.teaminvitation.service.dto.TeamInvitationEmailsGetResponse;
import com.tiki.server.teaminvitation.service.dto.TeamInvitationInformGetResponse;
import com.tiki.server.teaminvitation.messages.SuccessMessage;
import lombok.NonNull;
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
            final Principal principal,
            @PathVariable final long teamId
    ) {
        long memberId = Long.parseLong(principal.getName());
        TeamInvitationEmailsGetResponse response = teamInvitationService.getInvitations(memberId, teamId);
        return SuccessResponse.success(SuccessMessage.GET_TEAM_INVITATIONS.getMessage(), response);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/team/{teamId}")
    public SuccessResponse<?> deleteTeamInvitationFromAdmin(
            final Principal principal,
            @RequestParam @NonNull final String invitationId,
            @PathVariable final long teamId
    ) {
        long memberId = Long.parseLong(principal.getName());
        teamInvitationService.deleteTeamInvitationFromAdmin(memberId, teamId, invitationId);
        return SuccessResponse.success(SuccessMessage.DELETE_TEAM_INVITATION_FROM_ADMIN.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public SuccessResponse<TeamInvitationInformGetResponse> getInvitationInform(
            @RequestParam @NonNull final String invitationId
    ) {
        TeamInvitationInformGetResponse response = teamInvitationService.getInvitationInform(invitationId);
        return SuccessResponse.success(SuccessMessage.GET_TEAM_INVITATION_INFORM.getMessage(), response);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/team-member")
    public SuccessResponse<?> createTeamMemberFromInvitation(
            Principal principal,
            @RequestParam final long teamId,
            @RequestParam @NonNull final String teamInvitationId
    ) {
        long memberId = Long.parseLong(principal.getName());
        teamInvitationService.createTeamMemberFromInvitation(memberId, teamId, teamInvitationId);
        return SuccessResponse.success(SuccessMessage.CREATE_TEAM_MEMBER_FROM_INVITATION.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    public SuccessResponse<?> deleteTeamInvitationFromUser(
            Principal principal,
            @RequestParam @NonNull final String invitationId
    ) {
        long memberId = Long.parseLong(principal.getName());
        teamInvitationService.deleteTeamInvitation(memberId, invitationId);
        return SuccessResponse.success(SuccessMessage.DELETE_TEAM_INVITATION_FROM_USER.getMessage());
    }
}
