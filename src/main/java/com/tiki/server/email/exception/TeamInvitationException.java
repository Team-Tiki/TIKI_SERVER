package com.tiki.server.email.exception;

import com.tiki.server.email.teaminvitation.messages.ErrorCode;
import lombok.Getter;

@Getter
public class TeamInvitationException extends RuntimeException {

    private final ErrorCode errorCode;

    public TeamInvitationException(ErrorCode errorCode) {
        super("[TeamException] : " + errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
