package com.tiki.server.document.controller.docs;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.tiki.server.common.dto.ErrorResponse;
import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.document.dto.response.DocumentsGetResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "documents", description = "문서 API")
public interface DocumentControllerDocs {

	@Operation(
		summary = "전체 문서 조회",
		description = "전체 문서를 조회한다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "성공"),
			@ApiResponse(
				responseCode = "403",
				description = "접근 권한 없음",
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
				content = @Content(schema = @Schema(implementation = ErrorResponse.class)))}
	)
	ResponseEntity<SuccessResponse<DocumentsGetResponse>> getAllDocuments(
		@Parameter(hidden = true) Principal principal,
		@Parameter(
			name = "teamId",
			description = "팀 id",
			in = ParameterIn.PATH,
			example = "1"
		)
		@PathVariable long teamId,
		@Parameter(
			name = "type",
			description = "타임라인 타입",
			in = ParameterIn.QUERY,
			example = "executive, member"
		) @RequestParam String type
	);

	@Operation(
		summary = "문서 삭제",
		description = "문서를 삭제한다.",
		responses = {
			@ApiResponse(responseCode = "204", description = "성공"),
			@ApiResponse(
				responseCode = "403",
				description = "접근 권한 없음",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "404",
				description = "팀에 존재하지 않는 회원, 유효하지 않은 문서",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "4xx",
				description = "클라이언트(요청) 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "500",
				description = "서버 내부 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class)))}
	)
	ResponseEntity<?> deleteDocument(
		@Parameter(hidden = true) Principal principal,
		@Parameter(
			name = "teamId",
			description = "팀 id",
			in = ParameterIn.PATH,
			example = "1"
		)
		@PathVariable long teamId,
		@Parameter(
			name = "documentId",
			description = "문서 id",
			in = ParameterIn.PATH,
			example = "1"
		)
		@PathVariable long documentId
	);
}
