package com.tiki.server.email.teaminvitation.entity;

import static lombok.AccessLevel.PRIVATE;

import com.tiki.server.common.entity.BaseTime;
import com.tiki.server.email.Email;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@AllArgsConstructor(access = PRIVATE)
@RedisHash(value = "TeamInvitation", timeToLive = 604800)
public class TeamInvitation extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String sender;

    private long teamId;

    private Email email;

    private LocalDate expiredDate;

    public static TeamInvitation of(final String sender, final long teamId, final Email email) {
        return TeamInvitation.builder().id(teamId + email.getEmail()).sender(sender).teamId(teamId).email(email)
                .expiredDate(LocalDate.now()).build();
    }

    public String getEmailToString() {
        return email.getEmail();
    }
}
