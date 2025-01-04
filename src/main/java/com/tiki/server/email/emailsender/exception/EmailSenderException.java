package com.tiki.server.email.emailsender.exception;


import com.tiki.server.email.emailsender.message.ErrorCode;
import lombok.Getter;

@Getter
public class EmailSenderException extends RuntimeException {

    private final ErrorCode errorCode;

    public EmailSenderException(ErrorCode errorCode) {
        super("[EmailSenderException] : " + errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
