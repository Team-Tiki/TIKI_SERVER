package com.tiki.server.external.exception;

import com.tiki.server.external.message.ErrorCode;

import lombok.Getter;

@Getter
public class ExternalException extends RuntimeException {

	private final ErrorCode errorCode;

	public ExternalException(final ErrorCode errorCode) {
		super("[ExternalException] : " + errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
