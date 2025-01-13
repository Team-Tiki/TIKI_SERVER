package com.tiki.server.note.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tiki.server.common.dto.BaseResponse;
import com.tiki.server.note.service.dto.response.NoteFreeDetailGetServiceResponse;
import com.tiki.server.note.service.dto.response.NoteTemplateDetailGetServiceResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record SuccessNoteDetailResponse<T>(
        @NotNull boolean success,
        @NotNull String message,
        @Schema(
                description = "응답 데이터",
                oneOf = {
                        NoteFreeDetailGetServiceResponse.class,
                        NoteTemplateDetailGetServiceResponse.class
                }
        )
        @JsonInclude(value = NON_NULL) T data
) implements BaseResponse {

    public static <T> SuccessNoteDetailResponse<T> success(final String message, final T data) {
        return SuccessNoteDetailResponse.<T>builder().success(true).message(message).data(data).build();
    }

    public static SuccessNoteDetailResponse<?> success(final String message) {
        return SuccessNoteDetailResponse.builder().success(true).message(message).build();
    }
}