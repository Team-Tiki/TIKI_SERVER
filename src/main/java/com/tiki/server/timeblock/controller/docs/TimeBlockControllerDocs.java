package com.tiki.server.timeblock.controller.docs;

import com.tiki.server.timeblock.service.dto.response.AllTimeBlockServiceResponse;
import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.tiki.server.common.dto.ErrorResponse;
import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.timeblock.dto.request.TimeBlockCreateRequest;
import com.tiki.server.timeblock.dto.response.TimeBlockCreateResponse;
import com.tiki.server.timeblock.dto.response.TimeBlockDetailGetResponse;
import com.tiki.server.timeblock.dto.response.TimelineGetResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "time-blocks", description = "타임 블록 API")
public interface TimeBlockControllerDocs {

	@Operation(
		summary = "타임 블록 생성",
		description = "타임 블록을 생성한다.",
		responses = {
			@ApiResponse(responseCode = "201", description = "성공"),
			@ApiResponse(
				responseCode = "400",
				description = "타입 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "403",
				description = "접근 권한 없음",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "404",
				description = "팀에 존재하지 않는 회원, 유효하지 않은 팀",
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
	SuccessResponse<TimeBlockCreateResponse> createTimeBlock(
		@Parameter(hidden = true) final Principal principal,
		@Parameter(
			name = "teamId",
			description = "팀 id",
			in = ParameterIn.PATH,
			example = "1"
		)
		@PathVariable final long teamId,
		@Parameter(
			name = "type",
			description = "타임라인 타입",
			in = ParameterIn.QUERY,
			required = true,
			example = "executive, member"
		) @RequestParam final String type,
		@RequestBody final TimeBlockCreateRequest request
	);

	@Operation(
		summary = "타임라인 조회",
		description = "타임라인을 조회한다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "성공"),
			@ApiResponse(
				responseCode = "400",
				description = "타입 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "403",
				description = "접근 권한 없음",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "404",
				description = "팀에 존재하지 않는 회원, 유효하지 않은 팀",
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
	SuccessResponse<TimelineGetResponse> getTimeline(
		@Parameter(hidden = true) final Principal principal,
		@Parameter(
			name = "teamId",
			description = "팀 id",
			in = ParameterIn.PATH,
			example = "1"
		)
		@PathVariable final long teamId,
		@Parameter(
			name = "type",
			description = "타임라인 타입",
			in = ParameterIn.QUERY,
			required = true,
			example = "executive, member"
		) @RequestParam final String type,
		@Parameter(
			name = "date",
			description = "조회할 타임라인의 년도와 월 정보",
			in = ParameterIn.QUERY,
			required = true,
			example = "2024-07"
		) @RequestParam final String date
	);

	@Operation(
			summary = "타임 블록 전체 조회",
			description = "타임 블록을 전체 조회한다.",
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
	SuccessResponse<AllTimeBlockServiceResponse> getAllTimeBlock(
			@Parameter(hidden = true) final Principal principal,
			@Parameter(
					name = "teamId",
					description = "팀 id",
					in = ParameterIn.PATH,
					example = "1"
			)
			@PathVariable final long teamId
	);

	@Operation(
		summary = "타임 블록 상세 조회",
		description = "타임 블록을 상세 조회한다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "성공"),
			@ApiResponse(
				responseCode = "403",
				description = "접근 권한 없음",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "404",
				description = "팀에 존재하지 않는 회원, 유효하지 않은 타임 블록",
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
	SuccessResponse<TimeBlockDetailGetResponse> getTimeBlockDetail(
		@Parameter(hidden = true) final Principal principal,
		@Parameter(
			name = "teamId",
			description = "팀 id",
			in = ParameterIn.PATH,
			example = "1"
		)
		@PathVariable final long teamId,
		@Parameter(
			name = "timeBlockId",
			description = "타임 블록 id",
			in = ParameterIn.PATH,
			example = "1"
		)
		@PathVariable final long timeBlockId
	);

	@Operation(
		summary = "타임 블록 삭제",
		description = "타임 블록을 삭제한다.",
		responses = {
			@ApiResponse(responseCode = "204", description = "성공"),
			@ApiResponse(
				responseCode = "403",
				description = "접근 권한 없음",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "404",
				description = "팀에 존재하지 않는 회원, 유효하지 않은 타임 블록",
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
	void deleteTimeBlock(
		@Parameter(hidden = true) final Principal principal,
		@Parameter(
			name = "teamId",
			description = "팀 id",
			in = ParameterIn.PATH,
			example = "1"
		)
		@PathVariable final long teamId,
		@Parameter(
			name = "timeBlockId",
			description = "타임 블록 id",
			in = ParameterIn.PATH,
			example = "1"
		)
		@PathVariable final long timeBlockId
	);

	@Operation(
		summary = "타임 블록 파일 태그 추가",
		description = "타임 블록에 파일 태그를 추가한다.",
		responses = {
			@ApiResponse(responseCode = "201", description = "성공"),
			@ApiResponse(
				responseCode = "4xx",
				description = "클라이언트(요청) 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "500",
				description = "서버 내부 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class)))}
	)
	SuccessResponse<?> createDocumentTag(
		@Parameter(hidden = true) final Principal principal,
		@Parameter(
			name = "teamId",
			description = "팀 id",
			in = ParameterIn.PATH,
			example = "1"
		) @PathVariable final long teamId,
		@Parameter(
			name = "timeBlockId",
			description = "타임 블록 id",
			in = ParameterIn.PATH,
			example = "1"
		) @PathVariable final long timeBlockId,
		@Parameter(
			name = "documentId",
			description = "추가할 파일 id 리스트",
			in = ParameterIn.QUERY,
			required = true,
			example = "[1, 2]"
		) @RequestParam("documentId") final List<Long> documentIds
	);

	@Operation(
		summary = "타임 블록 파일 태그 삭제",
		description = "타임 블록의 파일 태그를 삭제한다.",
		responses = {
			@ApiResponse(responseCode = "204", description = "성공"),
			@ApiResponse(
				responseCode = "4xx",
				description = "클라이언트(요청) 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "500",
				description = "서버 내부 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class)))}
	)
	void deleteDocumentTag(
		@Parameter(hidden = true) final Principal principal,
		@Parameter(
			name = "teamId",
			description = "팀 id",
			in = ParameterIn.PATH,
			example = "1"
		)
		@PathVariable final long teamId,
		@Parameter(
			name = "timeBlockId",
			description = "타임 블록 id",
			in = ParameterIn.PATH,
			example = "1"
		)
		@PathVariable final long timeBlockId,
		@Parameter(
			name = "tagId",
			description = "삭제할 파일 태그 id 리스트",
			in = ParameterIn.QUERY,
			required = true,
			example = "[1, 2]"
		) @RequestParam("tagId") final List<Long> tagIds
	);
}
