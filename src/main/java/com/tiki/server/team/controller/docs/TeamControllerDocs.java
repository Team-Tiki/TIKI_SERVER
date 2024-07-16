package com.tiki.server.team.controller.docs;

import java.security.Principal;
import java.util.List;

import com.tiki.server.memberteammanager.dto.response.BelongTeamsResponse;
import com.tiki.server.team.dto.response.TeamsGetResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.tiki.server.common.dto.ErrorResponse;
import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.team.dto.request.TeamCreateRequest;
import com.tiki.server.team.dto.response.TeamCreateResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "teams", description = "팀 API")
public interface TeamControllerDocs {

    @Operation(
            summary = "팀 생성",
            description = "팀을 생성한다.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "성공",
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
    ResponseEntity<SuccessResponse<TeamCreateResponse>> createTeam(
            @Parameter(hidden = true) Principal principal,
            @RequestBody TeamCreateRequest request
    );

    @Operation(
            summary = "전체 팀 조회",
            description = "가입한 대학의 전체 팀을 조회한다.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "성공",
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
    ResponseEntity<SuccessResponse<TeamsGetResponse>> showAllTeam(Principal principal);

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
