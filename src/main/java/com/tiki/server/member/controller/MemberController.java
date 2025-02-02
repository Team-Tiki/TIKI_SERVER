package com.tiki.server.member.controller;

import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.member.controller.docs.MemberControllerDocs;
import com.tiki.server.member.dto.request.PasswordChangeRequest;
import com.tiki.server.member.dto.request.MemberProfileCreateRequest;
import com.tiki.server.member.dto.response.BelongTeamsGetResponse;

import com.tiki.server.member.dto.response.MemberInfoGetResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.tiki.server.member.service.MemberService;

import lombok.RequiredArgsConstructor;

import java.security.Principal;

import static com.tiki.server.member.message.SuccessMessage.SUCCESS_CHANGING_PASSWORD;
import static com.tiki.server.member.message.SuccessMessage.SUCCESS_CREATE_MEMBER;
import static com.tiki.server.member.message.SuccessMessage.SUCCESS_WITHDRAWAL;
import static com.tiki.server.team.message.SuccessMessage.SUCCESS_GET_JOINED_TEAM;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/members")
public class MemberController implements MemberControllerDocs {

    private final MemberService memberService;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public SuccessResponse<?> signUp(@RequestBody final MemberProfileCreateRequest request) {
        memberService.signUp(request);
        return SuccessResponse.success(SUCCESS_CREATE_MEMBER.getMessage());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/info")
    public SuccessResponse<MemberInfoGetResponse> getMemberInfo(final Principal principal) {
        long memberId = Long.parseLong(principal.getName());
        MemberInfoGetResponse response = memberService.getMemberInfo(memberId);
        return SuccessResponse.success(SUCCESS_CREATE_MEMBER.getMessage(), response);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/teams")
    public SuccessResponse<BelongTeamsGetResponse> getBelongTeam(
            final Principal principal
    ) {
        long memberId = Long.parseLong(principal.getName());
        BelongTeamsGetResponse response = memberService.findBelongTeams(memberId);
        return SuccessResponse.success(SUCCESS_GET_JOINED_TEAM.getMessage(), response);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/password")
    public SuccessResponse<?> changePassword(
            @RequestBody final PasswordChangeRequest passwordChangeRequest
    ) {
        memberService.changePassword(passwordChangeRequest);
        return SuccessResponse.success(SUCCESS_CHANGING_PASSWORD.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/withdrawal")
    public SuccessResponse<?> withdrawal(final Principal principal) {
        long memberId = Long.parseLong(principal.getName());
        memberService.withdrawal(memberId);
        return SuccessResponse.success(SUCCESS_WITHDRAWAL.getMessage());
    }
}
