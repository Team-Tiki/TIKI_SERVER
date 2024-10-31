package com.tiki.server.note.dto.response;

public record NoteCreateResponse(
        long noteId
) {
    public static NoteCreateResponse from(long noteId){
        return new NoteCreateResponse(noteId);
    }
}
