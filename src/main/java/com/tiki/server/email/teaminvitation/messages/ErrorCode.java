package com.tiki.server.email.teaminvitation.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    ALREADY_INVITED_MEMBER(BAD_REQUEST, "이미 존재하는 팀원입니다."),
    NOT_MATCHED_MEMBER_INFORM(BAD_REQUEST, "일치하지 않은 초대정보 입니다."),
    INVALID_TEAM_INVITATION(NOT_FOUND, "존재하지 않거나 만료된 초대정보입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
