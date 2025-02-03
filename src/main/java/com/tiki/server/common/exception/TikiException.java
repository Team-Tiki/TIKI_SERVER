package com.tiki.server.common.exception;

import lombok.Getter;

@Getter
public class TikiException extends RuntimeException {

    private final ErrorCode errorCode;

    public TikiException(final ErrorCode errorCode) {
        super("[TikiException] : " + errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
