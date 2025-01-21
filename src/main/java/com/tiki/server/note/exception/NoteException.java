package com.tiki.server.note.exception;

import com.tiki.server.note.message.ErrorCode;
import lombok.Getter;

@Getter
public class NoteException extends RuntimeException {

    private final ErrorCode errorCode;

    public NoteException(final ErrorCode errorCode) {
        super("[NoteException] : " + errorCode.getMessage());
        this.errorCode = errorCode;
    }
}