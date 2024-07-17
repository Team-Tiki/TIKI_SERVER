package com.tiki.server.auth.token.entity;

import lombok.Builder;
import lombok.NonNull;
import org.springframework.data.redis.core.RedisHash;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
@RedisHash(value = "refreshToken", timeToLive = 1209600000L)
public record Token(
        long id,
        @NonNull String refreshToken
) {
    public static Token of(long id, String refreshToken) {
        return Token.builder().id(id).refreshToken(refreshToken).build();
    }
}