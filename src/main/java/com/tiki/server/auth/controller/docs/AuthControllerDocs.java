package com.tiki.server.auth.controller.docs;

import com.tiki.server.auth.dto.request.SignInRequest;
import com.tiki.server.auth.dto.response.SignInGetResponse;
import com.tiki.server.auth.dto.response.ReissueGetResponse;
import com.tiki.server.common.dto.ErrorResponse;
import com.tiki.server.common.dto.SuccessResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "auth", description = "인증 API")
public interface AuthControllerDocs {

	@Operation(
		summary = "로그인",
		description = "로그인을 진행한다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "성공"),
			@ApiResponse(
				responseCode = "400",
				description = "일치하지 않은 비밀번호",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "404",
				description = "유효하지 않은 회원",
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
	ResponseEntity<SuccessResponse<SignInGetResponse>> signIn(@RequestBody final SignInRequest request);

	@Operation(
		summary = "엑세스 토큰 재발급",
		description = "엑세스 토큰 재발급 메서드입니다.",
		responses = {
			@ApiResponse(responseCode = "201", description = "성공"),
			@ApiResponse(
				responseCode = "401",
				description = "유효하지 않은 키, 인증되지 않은 사용자",
				content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
			@ApiResponse(
				responseCode = "404",
				description = "유효하지 않은 회원",
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
	ResponseEntity<SuccessResponse<ReissueGetResponse>> reissue(final HttpServletRequest request);
}
