package com.tiki.server.member.service;

import com.tiki.server.member.adapter.MemberFinder;
import com.tiki.server.member.dto.request.MemberProfileCreateRequest;
import com.tiki.server.member.entity.Member;
import com.tiki.server.member.exception.MemberException;
import com.tiki.server.member.message.ErrorCode;
import com.tiki.server.member.repository.MemberRepository;
import lombok.NonNull;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.s3.model.ObjectPart;

import java.net.http.HttpRequest;
import java.util.Optional;

import static com.tiki.server.member.message.ErrorCode.CONFLICT_MEMBER;
import static com.tiki.server.member.message.ErrorCode.UNMATCHED_PASSWORD;
import static sun.security.timestamp.TSResponse.BAD_REQUEST;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    MemberFinder memberFinder;

    public void signUp(@NonNull MemberProfileCreateRequest request) {
        emailCheck(request.email());
        passwordCheck(request.password(), request.passwordCk());

    }

    private void emailCheck(String email) {
        val member = memberFinder.findMemberByEmail(email);
        if (member.isPresent()) {
            throw new MemberException(CONFLICT_MEMBER);
        }
    }

    private void passwordCheck(String password, String passwordCk) {
        if (password.equals(passwordCk)) {
            throw new MemberException(UNMATCHED_PASSWORD);
        }
    }
}
