package com.tiki.server.email.teaminvitation.scheduler;

import com.tiki.server.email.teaminvitation.adapter.TeamInvitationDeleter;
import com.tiki.server.email.teaminvitation.adapter.TeamInvitationFinder;
import com.tiki.server.email.teaminvitation.entity.TeamInvitation;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class TeamInvitationScheduler {

    private final TeamInvitationDeleter teamInvitationDeleter;
    private final TeamInvitationFinder teamInvitationFinder;

    @Scheduled(cron = "0 0 0 * * ?")
    public void cleanUpExpiredInvites() {
        LocalDate now = LocalDate.now();
        List<TeamInvitation> expiredInvites = teamInvitationFinder.findByExpiredDate(now);
        if (!expiredInvites.isEmpty()) {
            teamInvitationDeleter.deleteAll(expiredInvites);
        }
    }
}
