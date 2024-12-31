package com.tiki.server.email.teaminvitation.controller;

import static com.tiki.server.common.dto.SuccessResponse.success;

import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.email.teaminvitation.service.TeamInvitationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/team-invitation")
public class TeamInvitationController {

    private final TeamInvitationService teamInvitationService;

    @GetMapping
    public ResponseEntity<SuccessResponse<InvitationInformGetResponse>> getInvitationInform(

    )

}
