package com.tiki.server.auth.exception;

import com.tiki.server.auth.message.ErrorCode;

import lombok.Getter;

@Getter
public class AuthException extends RuntimeException {

	private final ErrorCode errorCode;

	public AuthException(final ErrorCode errorCode) {
		super("[AuthException] : " + errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
