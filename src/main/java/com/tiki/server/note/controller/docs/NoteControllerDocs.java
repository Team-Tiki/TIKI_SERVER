package com.tiki.server.note.controller.docs;

import com.tiki.server.common.dto.BaseResponse;
import com.tiki.server.common.dto.ErrorResponse;
import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.common.entity.SortOrder;
import com.tiki.server.note.controller.dto.request.NoteFreeCreateRequest;
import com.tiki.server.note.controller.dto.request.NoteFreeUpdateRequest;
import com.tiki.server.note.controller.dto.request.NoteTemplateCreateRequest;
import com.tiki.server.note.controller.dto.request.NoteTemplateUpdateRequest;
import com.tiki.server.note.service.dto.response.NoteCreateServiceResponse;
import com.tiki.server.note.service.dto.response.NoteDetailGetServiceResponse;
import com.tiki.server.note.service.dto.response.NoteFreeDetailGetServiceResponse;
import com.tiki.server.note.service.dto.response.NoteListGetServiceResponse;

import com.tiki.server.note.service.dto.response.NoteTemplateDetailGetServiceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "notes", description = "노트 API")
public interface NoteControllerDocs {

    @Operation(
            summary = "자유 형식 노트 생성",
            description = "새로운 자유 형식 노트를 생성한다.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "노트 생성 성공"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "요청 데이터 오류",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "접근 권한 없음(토큰 에러)",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "팀에 존재하지 않는 회원",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "4xx",
                            description = "클라이언트(요청) 오류",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 내부 오류",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    ResponseEntity<SuccessResponse<NoteCreateServiceResponse>> createNoteFree(
            @Parameter(hidden = true) Principal principal,
            @RequestBody NoteFreeCreateRequest request
    );

    @Operation(
            summary = "템플릿 노트 생성",
            description = "새로운 템플릿 형식 노트를 생성한다.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "노트 생성 성공"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "요청 데이터 오류",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "접근 권한 없음(토큰 에러)",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "팀에 존재하지 않는 회원",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "4xx",
                            description = "클라이언트(요청) 오류",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 내부 오류",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    ResponseEntity<SuccessResponse<NoteCreateServiceResponse>> createNoteTemplate(
            @Parameter(hidden = true) Principal principal,
            @RequestBody NoteTemplateCreateRequest request
    );

    @Operation(
            summary = "자유 형식 노트 수정",
            description = "기존 자유 형식 노트를 수정한다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "노트 수정 성공"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "요청 데이터 오류",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "접근 권한 없음(토큰 에러)",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "팀에 존재하지 않는 회원",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "존재하지 않는 노트",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "4xx",
                            description = "클라이언트(요청) 오류",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 내부 오류",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    ResponseEntity<BaseResponse> updateNoteFree(
            @Parameter(hidden = true) Principal principal,
            @PathVariable long noteId,
            @RequestBody NoteFreeUpdateRequest request
    );

    @Operation(
            summary = "템플릿 노트 수정",
            description = "기존 템플릿 노트를 수정한다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "노트 수정 성공"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "요청 데이터 오류",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "접근 권한 없음(토큰 에러)",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "팀에 존재하지 않는 회원",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "존재하지 않는 노트",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "4xx",
                            description = "클라이언트(요청) 오류",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 내부 오류",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    ResponseEntity<BaseResponse> updateNoteTemplate(
            @Parameter(hidden = true) Principal principal,
            @PathVariable long noteId,
            @RequestBody NoteTemplateUpdateRequest request
    );

    @Operation(
            summary = "노트 목록 조회",
            description = "특정 팀의 노트 목록을 조회한다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "조회 성공"),
                    @ApiResponse(
                            responseCode = "403",
                            description = "접근 권한 없음(토큰 에러)",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "팀에 존재하지 않는 회원",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "4xx",
                            description = "클라이언트(요청) 오류",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 내부 오류",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    ResponseEntity<SuccessResponse<NoteListGetServiceResponse>> getNote(
            @Parameter(hidden = true) Principal principal,
            @PathVariable long teamId,
            @RequestParam(required = false) LocalDateTime createdAt,
            @RequestParam(defaultValue = "DESC") SortOrder sortOrder
    );

    @Operation(
            summary = "노트 상세 조회",
            description = "특정 노트의 상세 정보를 조회한다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "조회 성공",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(oneOf = {
                                            NoteFreeDetailGetServiceResponse.class,
                                            NoteTemplateDetailGetServiceResponse.class}))),
                    @ApiResponse(
                            responseCode = "403",
                            description = "접근 권한 없음(토큰 에러)",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "노트를 찾을 수 없음",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "팀에 존재하지 않는 회원",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "4xx",
                            description = "클라이언트(요청) 오류",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 내부 오류",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    ResponseEntity<SuccessResponse<NoteDetailGetServiceResponse>> getNoteDetail(
            @Parameter(hidden = true) Principal principal,
            @PathVariable long teamId,
            @PathVariable long noteId
    );

    @Operation(
            summary = "노트 삭제",
            description = "특정 팀의 노트를 삭제한다.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "삭제 성공"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "요청 데이터 오류",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "접근 권한 없음(토큰 에러)",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "팀에 존재하지 않는 회원",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "4xx",
                            description = "클라이언트(요청) 오류",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 내부 오류",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    ResponseEntity<SuccessResponse<Void>> deleteNotes(
            @Parameter(hidden = true) Principal principal,
            @PathVariable long teamId,
            @RequestParam List<Long> noteIds
    );
}