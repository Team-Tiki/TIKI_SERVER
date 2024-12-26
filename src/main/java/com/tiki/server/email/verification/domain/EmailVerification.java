package com.tiki.server.email.verification.domain;

import com.tiki.server.email.Email;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor(access = PRIVATE)
@Builder
@RedisHash(value = "mailVerification", timeToLive = 180)
public class EmailVerification {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String id;

    private VerificationCode verificationCode;

    public static EmailVerification of(Email email) {
        return EmailVerification.builder()
                .id(email.getEmail())
                .verificationCode(VerificationCode.from())
                .build();
    }

    public void verify(String code) {
        this.verificationCode.verify(code);
    }
}
