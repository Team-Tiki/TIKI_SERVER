package com.tiki.server.teaminvitation.entity;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

import com.tiki.server.common.entity.BaseTime;
import com.tiki.server.email.Email;
import jakarta.persistence.GeneratedValue;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@RedisHash(value = "TeamInvitation", timeToLive = 604800)
public class TeamInvitation extends BaseTime {

    @Id
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
