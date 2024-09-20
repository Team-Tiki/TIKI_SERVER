package com.tiki.server.member.controller;

import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.member.controller.docs.MemberControllerDocs;
import com.tiki.server.member.dto.request.PasswordChangeRequest;
import com.tiki.server.member.dto.request.MemberProfileCreateRequest;
import com.tiki.server.common.dto.BaseResponse;
import com.tiki.server.member.dto.response.BelongTeamsGetResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tiki.server.member.service.MemberService;

import lombok.RequiredArgsConstructor;

import java.security.Principal;

import static com.tiki.server.common.dto.SuccessResponse.success;
import static com.tiki.server.common.support.UriGenerator.getUri;
import static com.tiki.server.member.message.SuccessMessage.SUCCESS_CHANGING_PASSWORD;
import static com.tiki.server.member.message.SuccessMessage.SUCCESS_CREATE_MEMBER;
import static com.tiki.server.team.message.SuccessMessage.SUCCESS_GET_JOINED_TEAM;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/members")
public class MemberController implements MemberControllerDocs {

    private final MemberService memberService;

    @Override
    @PostMapping
    public ResponseEntity<BaseResponse> signUp(@RequestBody MemberProfileCreateRequest request) {
        memberService.signUp(request);
        return ResponseEntity.created(getUri("/")).body(success(SUCCESS_CREATE_MEMBER.getMessage()));
    }

    @Override
    @GetMapping("/teams")
    public ResponseEntity<SuccessResponse<BelongTeamsGetResponse>> getBelongTeam(
            Principal principal
    ) {
        long memberId = Long.parseLong(principal.getName());
        BelongTeamsGetResponse response = memberService.findBelongTeams(memberId);
        return ResponseEntity.ok().body(success(SUCCESS_GET_JOINED_TEAM.getMessage(), response));
    }

    @PatchMapping("/password")
    public ResponseEntity<BaseResponse> changePassword(
            @RequestBody PasswordChangeRequest passwordChangeRequest
    ) {
        memberService.changePassword(passwordChangeRequest);
        return ResponseEntity.ok().body(success(SUCCESS_CHANGING_PASSWORD.getMessage()));
    }
}
