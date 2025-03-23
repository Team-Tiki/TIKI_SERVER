package com.tiki.server.teaminvitation.exception;

import com.tiki.server.teaminvitation.messages.ErrorCode;
import lombok.Getter;

@Getter
public class TeamInvitationException extends RuntimeException {

    private final ErrorCode errorCode;

    public TeamInvitationException(ErrorCode errorCode) {
        super("[TeamInvitationException] : " + errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
