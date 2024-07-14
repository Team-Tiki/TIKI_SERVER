package com.tiki.server.external.controller.docs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.tiki.server.common.dto.BaseResponse;
import com.tiki.server.common.dto.ErrorResponse;
import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.external.dto.request.S3DeleteRequest;
import com.tiki.server.external.dto.response.PreSignedUrlResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "S3", description = "AWS S3 API")
public interface S3ControllerDocs {

	@Operation(
		summary = "Presigned Url 생성",
		description = "s3로부터 Presigned Url을 생성한다.",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "성공",
				content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
			@ApiResponse(
				responseCode = "4xx",
				description = "클라이언트(요청) 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "500",
				description = "S3 PRESIGNED URL 불러오기 실패",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class)))}
	)
	ResponseEntity<SuccessResponse<PreSignedUrlResponse>> getPreSignedUrl(
		@Parameter(
			name = "fileFormat",
			description = "파일 형식",
			in = ParameterIn.QUERY,
			example = "hwp, pdf, ..."
		) @RequestParam String fileFormat
	);

	@Operation(
		summary = "s3 파일 삭제",
		description = "s3의 파일 삭제한다.",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "성공",
				content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
			@ApiResponse(
				responseCode = "4xx",
				description = "클라이언트(요청) 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "500",
				description = "S3 버킷의 파일 삭제 실패",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class)))}
	)
	ResponseEntity<BaseResponse> deleteFile(
		@RequestBody S3DeleteRequest request
	);
}
