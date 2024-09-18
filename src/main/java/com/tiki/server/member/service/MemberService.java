package com.tiki.server.member.service;

import com.tiki.server.member.adapter.MemberFinder;
import com.tiki.server.member.adapter.MemberSaver;
import com.tiki.server.member.dto.request.PasswordChangeRequest;
import com.tiki.server.member.dto.request.MemberProfileCreateRequest;
import com.tiki.server.member.dto.response.BelongTeamsGetResponse;
import com.tiki.server.member.entity.Member;
import com.tiki.server.member.exception.MemberException;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import static com.tiki.server.member.message.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberSaver memberSaver;
    private final MemberFinder memberFinder;
    private final PasswordEncoder passwordEncoder;
    private final MemberTeamManagerFinder memberTeamManagerFinder;

    @Transactional
    public void signUp(MemberProfileCreateRequest request) {
        memberFinder.checkPresent(request.email());
        checkPassword(request.password(), request.passwordChecker());
        Member member = createMember(request);
        saveMember(member);
    }

    @Transactional
    public void changePassword(PasswordChangeRequest request) {
        Member member = memberFinder.checkEmpty(request.email());
        checkPassword(request.password(), request.passwordChecker());
        member.resetPassword(passwordEncoder.encode(request.password()));
    }

    private Member createMember(MemberProfileCreateRequest request) {
        return Member.of(
                request.email(),
                passwordEncoder.encode(request.password()),
                request.name(),
                request.birth(),
                request.univ());
    }

    private void checkPassword(String password, String passwordChecker) {
        if (!password.equals(passwordChecker)) {
            throw new MemberException(UNMATCHED_PASSWORD);
        }
    }

    private void saveMember(Member member) {
        memberSaver.save(member);
    }

    public BelongTeamsGetResponse findBelongTeams(long memberId) {
        return BelongTeamsGetResponse.from(memberTeamManagerFinder.findBelongTeamByMemberId(memberId));
    }
}
