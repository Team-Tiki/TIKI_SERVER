package com.tiki.server.mail.exception;

import com.tiki.server.mail.message.ErrorCode;
import lombok.Getter;

@Getter
public class MailException extends RuntimeException {

    private final ErrorCode errorCode;

    public MailException(ErrorCode errorCode) {
        super("[MailException] : " + errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
