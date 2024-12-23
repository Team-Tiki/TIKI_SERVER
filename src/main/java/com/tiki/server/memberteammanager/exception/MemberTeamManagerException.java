package com.tiki.server.memberteammanager.exception;

import com.tiki.server.memberteammanager.message.ErrorCode;

import lombok.Getter;

@Getter
public class MemberTeamManagerException extends RuntimeException {

	private final ErrorCode errorCode;

	public MemberTeamManagerException(final ErrorCode errorCode) {
		super("[MemberTeamManagerException] : " + errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
