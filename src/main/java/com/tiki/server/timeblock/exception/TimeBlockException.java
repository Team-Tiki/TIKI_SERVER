package com.tiki.server.timeblock.exception;

import com.tiki.server.timeblock.message.ErrorCode;

import lombok.Getter;

@Getter
public class TimeBlockException extends RuntimeException {

	private final ErrorCode errorCode;

	public TimeBlockException(final ErrorCode errorCode) {
		super("[TimeBlockException] : " + errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
