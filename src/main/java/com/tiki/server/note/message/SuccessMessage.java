package com.tiki.server.note.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    CREATE_NOTE_FREE("노트 생성 성공");

    private final String message;
}
