package com.tiki.server.mail.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import static jakarta.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@Getter
@RedisHash(value = "mailVerification", timeToLive = 180)
public class Mail {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String id;
    private String code;

    public static Mail of(String address, String code) {
        return new Mail(address, code);
    }
}
