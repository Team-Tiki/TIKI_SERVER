package com.tiki.server.folder.exception;

import com.tiki.server.folder.message.ErrorCode;

import lombok.Getter;

@Getter
public class FolderException extends RuntimeException {

	private final ErrorCode errorCode;

	public FolderException(ErrorCode errorCode) {
		super("[FolderException] : " + errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
