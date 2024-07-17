package com.tiki.server.member.entity;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

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

    public static Member of(String email, String password, String name, LocalDate birth, University univ) {
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .birth(birth)
                .univ(univ)
                .build();
    }

    public void resetPassword(String password) {
        this.password = password;
    }
}
