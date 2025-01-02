package com.tiki.server.member.exception;

import com.tiki.server.member.message.ErrorCode;

import lombok.Getter;

@Getter
public class MemberException extends RuntimeException {

	private final ErrorCode errorCode;

    public MemberException(final ErrorCode errorCode) {
        super("[MemberException] : " + errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
