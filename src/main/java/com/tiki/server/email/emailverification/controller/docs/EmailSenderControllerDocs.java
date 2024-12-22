package com.tiki.server.email.emailverification.controller.docs;

import com.tiki.server.common.dto.BaseResponse;
import com.tiki.server.common.dto.ErrorResponse;
import com.tiki.server.email.emailverification.dto.request.CodeVerificationRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "EmailVerification", description = "메일 인증 API")
public interface EmailSenderControllerDocs {

    @Operation(
            summary = "메일 인증",
            description = "인증번호 확인",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "이메일 형식 오류",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "403",
                            description = "인증 값 불일치",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "인증 정보가 존재하지 않음",
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
    ResponseEntity<BaseResponse> checkCode(@RequestBody CodeVerificationRequest verificationCodeRequest);
}
