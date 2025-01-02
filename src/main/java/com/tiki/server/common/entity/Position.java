package com.tiki.server.common.entity;

import static com.tiki.server.timeblock.message.ErrorCode.INVALID_TYPE;

import com.tiki.server.timeblock.exception.TimeBlockException;

import lombok.Getter;

@Getter
public enum Position {
	ADMIN(1), EXECUTIVE(2), MEMBER(3);

	private final int authorization;

	Position(final int authorization) {
		this.authorization = authorization;
	}

	public static Position getAccessiblePosition(final String type) {
		return switch (type) {
			case "executive" -> EXECUTIVE;
			case "member" -> MEMBER;
			default -> throw new TimeBlockException(INVALID_TYPE);
		};
	}
}
