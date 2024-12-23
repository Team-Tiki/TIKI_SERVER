package com.tiki.server.drive.controller.docs;

import java.security.Principal;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.tiki.server.common.dto.ErrorResponse;
import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.drive.dto.DriveGetResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "drive", description = "드라이브 API")
public interface DriveControllerDocs {

	@Operation(
		summary = "드라이브 조회",
		description = "드라이브 뷰를 조회한다.",
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
	SuccessResponse<DriveGetResponse> getDrive(
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
}
