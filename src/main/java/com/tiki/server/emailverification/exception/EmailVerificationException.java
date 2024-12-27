package com.tiki.server.emailverification.exception;

import com.tiki.server.emailverification.message.ErrorCode;
import lombok.Getter;

@Getter
public class EmailVerificationException extends RuntimeException {

    private final ErrorCode errorCode;

    public EmailVerificationException(final ErrorCode errorCode) {
        super("[EmailVerificationException] : " + errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
