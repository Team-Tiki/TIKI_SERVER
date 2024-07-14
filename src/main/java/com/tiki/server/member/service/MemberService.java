package com.tiki.server.member.service;

import com.tiki.server.member.adapter.MemberFinder;
import com.tiki.server.member.adapter.MemberSaver;
import com.tiki.server.member.dto.request.MemberProfileCreateRequest;
import com.tiki.server.member.entity.Member;
import com.tiki.server.member.exception.MemberException;
import lombok.NonNull;
import lombok.val;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import static com.tiki.server.member.message.ErrorCode.CONFLICT_MEMBER;
import static com.tiki.server.member.message.ErrorCode.UNMATCHED_PASSWORD;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberSaver memberSaver;
    private final MemberFinder memberFinder;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(MemberProfileCreateRequest request) {
        mailCheck(request.email());
        passwordCheck(request.password(), request.passwordChecker());
        val member = createMember(request);
        saveMember(member);
    }

    private void mailCheck(String email) {
        memberFinder.findByEmail(email).ifPresent(member -> {
            throw new MemberException(CONFLICT_MEMBER);
        });
    }

    private void passwordCheck(String password, String passwordChecker) {
        if (!password.equals(passwordChecker)) {
            throw new MemberException(UNMATCHED_PASSWORD);
        }
    }

    private Member createMember(MemberProfileCreateRequest request) {
        return Member.of(
                request.email(),
                passwordEncoder.encode(request.password()),
                request.name(),
                request.birth(),
                request.Univ());
    }

    private void saveMember(Member member) {
        memberSaver.save(member);
    }

}
