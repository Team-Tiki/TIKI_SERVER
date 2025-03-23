package com.tiki.server.email.teaminvitation.entity;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

import com.tiki.server.common.entity.BaseTime;
import com.tiki.server.email.Email;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
public class TeamInvitation extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invitation_id")
    private Long id;

    private String sender;

    private long teamId;

    private Email email;

    private LocalDate expiredDate;

    public static TeamInvitation of(final String sender, final long teamId, final Email email) {
        return TeamInvitation.builder().sender(sender).teamId(teamId).email(email).expiredDate(LocalDate.now()).build();
    }

    public String getEmailToString(){
        return email.getEmail();
    }
}
