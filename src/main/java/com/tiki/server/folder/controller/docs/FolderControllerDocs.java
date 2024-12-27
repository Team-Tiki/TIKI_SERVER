package com.tiki.server.folder.controller.docs;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.tiki.server.common.dto.ErrorResponse;
import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.folder.dto.request.FolderCreateRequest;
import com.tiki.server.folder.dto.request.FolderNameUpdateRequest;
import com.tiki.server.folder.dto.response.FolderCreateResponse;
import com.tiki.server.folder.dto.response.FoldersGetResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "folders", description = "폴더 API")
public interface FolderControllerDocs {

	@Operation(
		summary = "폴더 조회",
		description = "폴더를 여러 개 조회한다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "성공"),
			@ApiResponse(
				responseCode = "4xx",
				description = "클라이언트(요청) 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "500",
				description = "서버 내부 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class)))}
	)
	SuccessResponse<FoldersGetResponse> getFolders(
		@Parameter(hidden = true) final Principal principal,
		@Parameter(
			name = "teamId",
			description = "팀 id",
			in = ParameterIn.PATH,
			required = true,
			example = "1"
		) @PathVariable final long teamId,
		@Parameter(
			name = "folderId",
			description = "조회할 폴더 id (최상단은 비워두기)",
			in = ParameterIn.QUERY,
			example = "1"
		) @RequestParam final Long folderId
	);

	@Operation(
		summary = "폴더 생성",
		description = "폴더를 생성한다.",
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
	SuccessResponse<FolderCreateResponse> createFolder(
		@Parameter(hidden = true) final Principal principal,
		@Parameter(
			name = "teamId",
			description = "팀 id",
			in = ParameterIn.PATH,
			required = true,
			example = "1"
		) @PathVariable final long teamId,
		@Parameter(
			name = "folderId",
			description = "생성할 폴더가 속할 폴더 id (최상단은 비워두기)",
			in = ParameterIn.QUERY,
			example = "1"
		) @RequestParam final Long folderId,
		@RequestBody final FolderCreateRequest request
	);

	@Operation(
		summary = "폴더 이름 수정",
		description = "폴더 이름을 수정한다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "성공"),
			@ApiResponse(
				responseCode = "4xx",
				description = "클라이언트(요청) 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "500",
				description = "서버 내부 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class)))}
	)
	SuccessResponse<?> updateFolderName(
		@Parameter(hidden = true) final Principal principal,
		@Parameter(
			name = "teamId",
			description = "팀 id",
			in = ParameterIn.PATH,
			required = true,
			example = "1"
		) @PathVariable final long teamId,
		@Parameter(
			name = "folderId",
			description = "수정할 폴더 id",
			in = ParameterIn.PATH,
			example = "1"
		) @PathVariable final long folderId,
		@RequestBody final FolderNameUpdateRequest request
	);

	@Operation(
		summary = "폴더 삭제",
		description = "폴더를 여러 개 삭제한다.",
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
	void delete(
		@Parameter(hidden = true) final Principal principal,
		@Parameter(
			name = "teamId",
			description = "팀 id",
			in = ParameterIn.PATH,
			required = true,
			example = "1"
		) @PathVariable final long teamId,
		@Parameter(
			name = "folderId",
			description = "삭제할 폴더 id 리스트",
			in = ParameterIn.QUERY,
			required = true,
			example = "[1, 2]"
		) @RequestParam("folderId") final List<Long> folderIds
	);
}
