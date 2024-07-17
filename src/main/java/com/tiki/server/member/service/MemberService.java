package com.tiki.server.member.service;

import com.tiki.server.member.adapter.MemberFinder;
import com.tiki.server.member.adapter.MemberSaver;
import com.tiki.server.member.dto.request.PasswordChangeRequest;
import com.tiki.server.member.dto.request.MemberProfileCreateRequest;
import com.tiki.server.member.dto.response.BelongTeamsGetResponse;
import com.tiki.server.member.entity.Member;
import com.tiki.server.member.exception.MemberException;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import lombok.val;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import static com.tiki.server.mail.constants.MailConstants.MAIL_FORMAT_AC_KR;
import static com.tiki.server.mail.constants.MailConstants.MAIL_FORMAT_EDU;
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
        checkMailFormat(request.email());
        checkMailDuplicate(request.email());
        checkPassword(request.password(), request.passwordChecker());
        val member = createMember(request);
        saveMember(member);
    }

    @Transactional
    public void changePassword(PasswordChangeRequest request) {
        val member = checkMemberEmpty(request);
        checkPassword(request.password(), request.passwordChecker());
        member.resetPassword(passwordEncoder.encode(request.password()));
    }

    private Member checkMemberEmpty(PasswordChangeRequest request) {
        return memberFinder.findByEmail(request.email()).orElseThrow(() -> new MemberException(INVALID_MEMBER));
    }

    private void checkMailDuplicate(String email) {
        memberFinder.findByEmail(email).ifPresent(member -> {
            throw new MemberException(CONFLICT_MEMBER);
        });
    }
    private void checkMailFormat(String email){
        if (!(email.endsWith(MAIL_FORMAT_EDU) || email.endsWith(MAIL_FORMAT_AC_KR))) {
            throw new MemberException(INVALID_EMAIL);
        }
    }

    private void checkPassword(String password, String passwordChecker) {
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
                request.univ());
    }

    private void saveMember(Member member) {
        memberSaver.save(member);
    }

    public BelongTeamsGetResponse findBelongTeams(long memberId) {
        return BelongTeamsGetResponse.from(memberTeamManagerFinder.findBelongTeamByMemberId(memberId));
    }
}
