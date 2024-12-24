package com.tiki.server.auth.controller;

import com.tiki.server.auth.controller.docs.AuthControllerDocs;
import com.tiki.server.auth.dto.request.SignInRequest;
import com.tiki.server.auth.dto.response.ReissueGetResponse;
import com.tiki.server.auth.dto.response.SignInGetResponse;
import com.tiki.server.common.dto.SuccessResponse;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tiki.server.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

import static com.tiki.server.auth.message.SuccessMessage.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController implements AuthControllerDocs {

    private final AuthService authService;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign-in")
    public SuccessResponse<SignInGetResponse> signIn(@RequestBody final SignInRequest request) {
        SignInGetResponse response = authService.signIn(request);
        return SuccessResponse.success(SUCCESS_SIGN_IN.getMessage(), response);
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/reissue")
    public SuccessResponse<ReissueGetResponse> reissue(final HttpServletRequest httpServletRequest) {
        ReissueGetResponse response = authService.reissueToken(httpServletRequest);
        return SuccessResponse.success(SUCCESS_REISSUE_ACCESS_TOKEN.getMessage(), response);
    }
}
