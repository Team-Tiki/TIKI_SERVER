package com.tiki.server.email.emailsender.service;

import com.tiki.server.common.entity.Position;
import com.tiki.server.email.emailsender.entity.MailSender;
import com.tiki.server.email.emailsender.service.dto.EmailServiceRequest;
import com.tiki.server.email.emailsender.service.dto.TeamInvitationCreateServiceRequest;
import com.tiki.server.email.teaminvitation.adapter.TeamInvitationFinder;
import com.tiki.server.email.teaminvitation.adapter.TeamInvitationSaver;
import com.tiki.server.email.teaminvitation.entity.TeamInvitation;
import com.tiki.server.email.verification.adapter.EmailVerificationSaver;
import com.tiki.server.email.verification.domain.EmailVerification;
import com.tiki.server.member.adapter.MemberFinder;
import com.tiki.server.member.entity.Member;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import com.tiki.server.memberteammanager.entity.MemberTeamManager;
import com.tiki.server.team.adapter.TeamFinder;
import com.tiki.server.team.entity.Team;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmailSenderService {

    private final MemberFinder memberFinder;
    private final TeamFinder teamFinder;
    private final MailSender mailSender;
    private final EmailVerificationSaver emailVerificationSaver;
    private final MemberTeamManagerFinder memberTeamManagerFinder;
    private final TeamInvitationSaver teamInvitationSaver;
    private final TeamInvitationFinder teamInvitationFinder;

    public void sendSignUp(final EmailServiceRequest emailServiceRequest) {
        memberFinder.checkPresent(emailServiceRequest.email());
        EmailVerification emailVerification = EmailVerification.of(emailServiceRequest.email());
        mailSender.sendVerificationMail(
                emailVerification.getId(),
                emailVerification.getVerificationCode().getCode()
        );
        emailVerificationSaver.save(emailVerification);
    }

    public void sendPasswordChanging(final EmailServiceRequest emailServiceRequest) {
        Member member = memberFinder.checkEmpty(emailServiceRequest.email());
        EmailVerification emailVerification = EmailVerification.of(emailServiceRequest.email());
        mailSender.sendPasswordChangingMail(
                emailVerification.getId(),
                emailVerification.getVerificationCode().getCode(),
                member.getName()
        );
        emailVerificationSaver.save(emailVerification);
    }

    public void createTeamInvitation(final TeamInvitationCreateServiceRequest request) {
        MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(request.senderId(),
                request.teamId());
        memberTeamManager.checkMemberAccessible(Position.ADMIN);
        Team team = teamFinder.findById(request.teamId());
        mailSender.sendTeamInvitationMail(
                request.email().getEmail(),
                memberTeamManager.getName(),
                team.getName(),
                request.teamId()
        );
        teamInvitationSaver.createTeamInvitation(TeamInvitation.of(memberTeamManager.getName(), request.teamId(), request.email()));
    }
}
