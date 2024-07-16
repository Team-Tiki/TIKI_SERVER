package com.tiki.server.auth.token.entity;

import lombok.Builder;
import org.springframework.data.redis.core.RedisHash;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
@RedisHash(value = "refreshToken", timeToLive = 1209600000)
public record Token(
        long id,
        String refreshToken
) {
    public static Token of(long id, String refreshToken) {
        return Token.builder().id(id).refreshToken(refreshToken).build();
    }
}