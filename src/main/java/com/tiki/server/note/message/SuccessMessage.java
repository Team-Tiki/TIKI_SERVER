package com.tiki.server.note.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    GET_NOTE("노트 조회 성공"),
    GET_NOTE_DETAIL("노트 상세 조회 성공"),
    CREATE_NOTE("노트 생성 성공"),
    DELETE_NOTE("노트 삭제 성공");

    private final String message;
}
