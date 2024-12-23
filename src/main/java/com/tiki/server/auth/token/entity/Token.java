package com.tiki.server.auth.token.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import org.springframework.data.redis.core.RedisHash;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
@RedisHash(value = "refreshToken", timeToLive = 1209600000L)
public record Token(
	@NotNull long id,
	@NotNull String refreshToken
) {
	public static Token of(final long id, final String refreshToken) {
		return Token.builder()
			.id(id)
			.refreshToken(refreshToken)
			.build();
	}
}