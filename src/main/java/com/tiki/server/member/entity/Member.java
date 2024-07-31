package com.tiki.server.member.entity;

import static com.tiki.server.mail.constants.MailConstants.MAIL_FORMAT_AC_KR;
import static com.tiki.server.mail.constants.MailConstants.MAIL_FORMAT_EDU;
import static com.tiki.server.member.message.ErrorCode.INVALID_EMAIL;
import static com.tiki.server.member.message.ErrorCode.UNMATCHED_PASSWORD;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

import com.tiki.server.member.exception.MemberException;
import java.time.LocalDate;

import com.tiki.server.common.entity.BaseTime;

import com.tiki.server.common.entity.University;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member extends BaseTime {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;

    private String password;

    private String name;

    private LocalDate birth;

    @Enumerated(value = STRING)
    private University univ;

    public static Member of(
            String email,
            String password,
            String passwordChecker,
            String name,
            LocalDate birth,
            University univ) {
        val member = Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .birth(birth)
                .univ(univ)
                .build();

        member.checkMailFormat();
        return member;
    }

    public void resetPassword(String password) {
        this.password = password;
    }

    private void checkMailFormat() {
        if (!(this.email.endsWith(MAIL_FORMAT_EDU) || this.email.endsWith(MAIL_FORMAT_AC_KR))) {
            throw new MemberException(INVALID_EMAIL);
        }
    }
}
