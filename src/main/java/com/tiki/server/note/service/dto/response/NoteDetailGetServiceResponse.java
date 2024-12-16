package com.tiki.server.note.service.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "NoteDetail 응답 객체",
        oneOf = {NoteFreeDetailGetServiceResponse.class, NoteTemplateDetailGetServiceResponse.class}
)
public interface NoteDetailGetServiceResponse {
}