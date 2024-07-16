package com.tiki.server.member.controller.docs;

import com.tiki.server.common.dto.BaseResponse;
import com.tiki.server.common.dto.ErrorResponse;
import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.member.dto.request.MemberProfileCreateRequest;
import com.tiki.server.memberteammanager.dto.response.BelongTeamsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;

public interface MemberControllerDocs {

    @Operation(
            summary = "회원가입 API",
            description = "회원가입을 위한 정보를 보낸다.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "회원가입 성공",
                            content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
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
            summary = "타임 블록 생성",
            description = "타임 블록을 생성한다.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "성공",
                            content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
                    @ApiResponse(
                            responseCode = "403",
                            description = "접근 권한 없음",
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
    ResponseEntity<SuccessResponse<List<BelongTeamsResponse>>> showBelongTeam(
            @Parameter(hidden = true) Principal principal
    );
}
