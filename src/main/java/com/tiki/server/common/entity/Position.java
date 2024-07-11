package com.tiki.server.common.entity;

import lombok.Getter;

@Getter
public enum Position {
	ADMIN(1), EXECUTIVE(2), MEMBER(3);

	private final int authorization;

	Position(int authorization) {
		this.authorization = authorization;
	}
}
