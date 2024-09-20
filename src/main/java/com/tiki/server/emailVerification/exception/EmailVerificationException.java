package com.tiki.server.emailVerification.exception;

import com.tiki.server.emailVerification.message.ErrorCode;
import lombok.Getter;

@Getter
public class EmailVerificationException extends RuntimeException {

    private final ErrorCode errorCode;

    public EmailVerificationException(ErrorCode errorCode) {
        super("[EmailVerificationException] : " + errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
