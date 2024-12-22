package com.tiki.server.email.emailverification.exception;

import com.tiki.server.email.emailverification.message.ErrorCode;
import lombok.Getter;

@Getter
public class EmailVerificationException extends RuntimeException {

    private final ErrorCode errorCode;

    public EmailVerificationException(ErrorCode errorCode) {
        super("[EmailVerificationException] : " + errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
