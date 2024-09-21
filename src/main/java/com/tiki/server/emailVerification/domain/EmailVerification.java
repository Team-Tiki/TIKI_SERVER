package com.tiki.server.emailVerification.domain;

import com.tiki.server.common.entity.Email;
import com.tiki.server.emailVerification.exception.EmailVerificationException;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import static com.tiki.server.emailVerification.message.ErrorCode.INVALID_MATCHED;
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

    private String code;

    public static EmailVerification of(Email email, String code) {
        return EmailVerification.builder().id(email.getEmail()).code(code).build();
    }

    public void verify(String code){
        if(!this.code.equals(code)){
            throw new EmailVerificationException(INVALID_MATCHED);
        }
    }
}
