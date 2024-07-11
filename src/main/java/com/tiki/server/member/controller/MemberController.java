package com.tiki.server.member.controller;

import com.tiki.server.member.dto.request.MemberProfileCreateRequest;
import com.tiki.server.common.dto.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiki.server.member.service.MemberService;

import lombok.RequiredArgsConstructor;

import static com.tiki.server.common.dto.SuccessResponse.success;
import static com.tiki.server.common.support.UriGenerator.getUri;
import static com.tiki.server.member.message.SuccessMessage.SUCCESS_CREATE_MEMBER;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<BaseResponse> signUp(
            @RequestBody MemberProfileCreateRequest request
    ) {
        memberService.signUp(request);
        return ResponseEntity.created(getUri("/")).body(success(SUCCESS_CREATE_MEMBER.getMessage()));
    }
}
