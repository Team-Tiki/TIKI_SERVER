package com.tiki.server.team.exception;

import com.tiki.server.team.message.ErrorCode;

import lombok.Getter;

@Getter
public class TeamException extends RuntimeException {

	private final ErrorCode errorCode;

	public TeamException(ErrorCode errorCode) {
		super("[TeamException] : " + errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
