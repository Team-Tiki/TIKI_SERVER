package com.tiki.server.email.teaminvitation.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    TEMP(BAD_REQUEST,"임시");

    private final HttpStatus httpStatus;
    private final String message;
}
