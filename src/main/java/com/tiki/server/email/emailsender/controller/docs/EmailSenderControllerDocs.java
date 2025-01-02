package com.tiki.server.email.emailsender.controller.docs;

import com.tiki.server.common.dto.ErrorResponse;
import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.email.emailsender.controller.dto.request.EmailRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "EmailVerification", description = "메일 인증 API")
public interface EmailSenderControllerDocs {

    @Operation(
            summary = "회원가입 메일 전송",
            description = "회원 가입을 진행한다.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "성공"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "이메일 형식 오류",
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
    SuccessResponse<?> sendSignUpMail(@RequestBody EmailRequest mailRequest);

    @Operation(
            summary = "비밀번호 재설정 메일 전송",
            description = "비밀번호 재설정을 위한 이메일을 보낸다.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "성공"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "이메일 형식 오류",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "가입되지 않은 이메일",
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
    SuccessResponse<?> sendChangingPasswordMail(@RequestBody EmailRequest mailRequest);
}
