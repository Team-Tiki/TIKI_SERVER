package com.tiki.server.member.service;

import com.tiki.server.member.adapter.MemberDeleter;
import com.tiki.server.member.adapter.MemberFinder;
import com.tiki.server.member.adapter.MemberSaver;
import com.tiki.server.member.dto.request.PasswordChangeRequest;
import com.tiki.server.member.dto.request.MemberProfileCreateRequest;
import com.tiki.server.member.dto.response.BelongTeamsGetResponse;
import com.tiki.server.email.Email;
import com.tiki.server.member.entity.Member;
import com.tiki.server.member.exception.MemberException;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerDeleter;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import com.tiki.server.memberteammanager.entity.MemberTeamManager;
import com.tiki.server.note.adapter.NoteFinder;
import com.tiki.server.note.entity.Note;
import com.tiki.server.team.adapter.TeamFinder;
import com.tiki.server.team.entity.Team;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.regex.Pattern;

import static com.tiki.server.member.Constants.PASSWORD_PATTERN;
import static com.tiki.server.member.message.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberSaver memberSaver;
    private final MemberFinder memberFinder;
    private final MemberDeleter memberDeleter;
    private final PasswordEncoder passwordEncoder;
    private final TeamFinder teamFinder;
    private final NoteFinder noteFinder;
    private final MemberTeamManagerFinder memberTeamManagerFinder;
    private final MemberTeamManagerDeleter memberTeamManagerDeleter;

    @Transactional
    public void signUp(final MemberProfileCreateRequest request) {
        memberFinder.checkPresent(Email.from(request.email()));
        checkPasswordFormat(request.password());
        checkPasswordMatch(request.password(), request.passwordChecker());
        Member member = createMember(request);
        saveMember(member);
    }

    public BelongTeamsGetResponse findBelongTeams(final long memberId) {
        List<MemberTeamManager> memberTeamManagers = memberTeamManagerFinder.findAllByMemberIdOrderByCreatedAt(memberId);
        List<Team> teams = getTeams(memberTeamManagers);
        return BelongTeamsGetResponse.from(teams);
    }

    @Transactional
    public void changePassword(final PasswordChangeRequest request) {
        Member member = memberFinder.checkEmpty(Email.from(request.email()));
        checkPasswordFormat(request.password());
        checkPasswordMatch(request.password(), request.passwordChecker());
        member.resetPassword(passwordEncoder.encode(request.password()));
    }

    @Transactional
    public void withdrawal(final long memberId) {
        Member member = memberFinder.findById(memberId);
        List<MemberTeamManager> memberTeamManagers = memberTeamManagerFinder.findAllByMemberIdOrderByCreatedAt(memberId);
        for (MemberTeamManager memberTeamManager : memberTeamManagers) {
            Team team = teamFinder.findById(memberTeamManager.getTeamId());
            memberTeamManager.checkMemberIsNotAdmin();
            List<Note> notes = noteFinder.findAllByMemberIdAndTeamId(memberId, team.getId());
            notes.forEach(Note::deleteMemberDependency);
            memberTeamManagerDeleter.delete(memberTeamManager);
        }
        memberDeleter.delete(member);
    }

    private Member createMember(final MemberProfileCreateRequest request) {
        return Member.of(
                request.email(),
                passwordEncoder.encode(request.password()),
                request.name(),
                request.birth(),
                request.univ());
    }

    private void saveMember(final Member member) {
        memberSaver.save(member);
    }

    private List<Team> getTeams(final List<MemberTeamManager> memberTeamManagers) {
        return memberTeamManagers.stream()
            .map(memberTeamManager -> teamFinder.findById(memberTeamManager.getTeamId()))
            .toList();
    }

    private void checkPasswordFormat(final String password) {
        if (!(password != null && !password.contains(" ") && Pattern.matches(PASSWORD_PATTERN, password))) {
            throw new MemberException(INVALID_PASSWORD);
        }
    }

    private void checkPasswordMatch(final String password, final String passwordChecker) {
        if (!password.equals(passwordChecker)) {
            throw new MemberException(UNMATCHED_PASSWORD);
        }
    }
}
