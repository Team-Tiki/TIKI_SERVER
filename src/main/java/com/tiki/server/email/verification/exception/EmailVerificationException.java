package com.tiki.server.email.verification.exception;

import com.tiki.server.email.verification.message.ErrorCode;
import lombok.Getter;

@Getter
public class EmailVerificationException extends RuntimeException {

    private final ErrorCode errorCode;

    public EmailVerificationException(final ErrorCode errorCode) {
        super("[EmailVerificationException] : " + errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
