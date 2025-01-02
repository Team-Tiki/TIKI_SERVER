package com.tiki.server.document.exception;

import com.tiki.server.document.message.ErrorCode;

import lombok.Getter;

@Getter
public class DocumentException extends RuntimeException {

	private final ErrorCode errorCode;

	public DocumentException(final ErrorCode errorCode) {
		super("[DocumentException] : " + errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
