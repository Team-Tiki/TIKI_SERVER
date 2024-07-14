package com.tiki.server.mail.entity;

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
public class Mail {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String id;

    private String code;

    public static Mail of(String address, String code) {
        return Mail.builder().id(address).code(code).build();
    }
}
