package com.tiki.server.member.controller.docs;

import com.tiki.server.common.dto.BaseResponse;
import com.tiki.server.common.dto.ErrorResponse;
import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.member.dto.request.PasswordChangeRequest;
import com.tiki.server.member.dto.request.MemberProfileCreateRequest;
import com.tiki.server.member.dto.response.BelongTeamsGetResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@Tag(name = "members", description = "멤버 API")
public interface MemberControllerDocs {

	@Operation(
		summary = "회원가입 API",
		description = "회원가입을 위한 정보를 보낸다.",
		responses = {
			@ApiResponse(responseCode = "201", description = "성공"),
			@ApiResponse(
				responseCode = "400",
				description = "이메일 형식 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "400",
				description = "비밀번호 불일치",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "409",
				description = "이미 가입된 아이디",
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
	ResponseEntity<BaseResponse> signUp(@RequestBody MemberProfileCreateRequest request);

	@Operation(
		summary = "소속 팀 가져오기",
		description = "왼쪽 사이드바의 소속된 팀 정보를 가져옵니다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "성공"),
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
	ResponseEntity<SuccessResponse<BelongTeamsGetResponse>> getBelongTeam(
		@Parameter(hidden = true) Principal principal
	);

	@Operation(
		summary = "비밀번호 변경",
		description = "비밀번호를 변경합니다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "성공"),
			@ApiResponse(
				responseCode = "400",
				description = "비밀번호가 일치하지 않습니다.",
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
	ResponseEntity<BaseResponse> changePassword(
		@RequestBody PasswordChangeRequest passwordChangeRequest
	);
}
