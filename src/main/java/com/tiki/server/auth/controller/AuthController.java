package com.tiki.server.auth.controller;
import com.tiki.server.auth.dto.request.LoginRequest;
import com.tiki.server.auth.dto.response.ReissueGetResponse;
import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.common.support.CookieGenerator;
import com.tiki.server.common.support.UriGenerator;
import com.tiki.server.member.dto.response.SignInResultGetResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tiki.server.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

import static com.tiki.server.auth.message.SuccessMessage.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<SuccessResponse<SignInResultGetResponse>> signIn(
            HttpServletResponse httpServletResponse,
            @RequestBody LoginRequest request
    ) {
        val response = authService.login(request, httpServletResponse);
        val cookie = CookieGenerator.setRefreshTokenToCookie(response.refreshToken());
        httpServletResponse.setHeader("Set-Cookie", cookie.toString());
        return ResponseEntity.created(UriGenerator.getUri("/"))
                .body(SuccessResponse.success(SUCCESS_SIGN_IN.getMessage(),
                        SignInResultGetResponse.from(response.accessToken())));
    }

    @GetMapping("/reissue")
    public ResponseEntity<SuccessResponse<ReissueGetResponse>> reissue(
            @CookieValue(name = "refreshToken", required = false)String refreshToken
    ) {
        val response = authService.reissueToken(refreshToken);
        return ResponseEntity.created(UriGenerator.getUri("/"))
                .body(SuccessResponse.success(SUCCESS_REISSUE_ACCESS_TOKEN.getMessage(), response));
    }
}
