package com.tiki.server.auth.controller;

import com.tiki.server.auth.dto.request.LoginRequest;
import com.tiki.server.auth.dto.response.UserAllTokenGetResponse;
import com.tiki.server.auth.dto.response.UserTokenGetResponse;
import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.common.support.UriGenerator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tiki.server.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

import static com.tiki.server.auth.message.SuccessMessage.SUCCESS_GENERATE_ACCESS_TOKEN;
import static com.tiki.server.auth.message.SuccessMessage.SUCCESS_SIGN_IN;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

	private final AuthService authService;

	@GetMapping("/{memberId}")
	public String getAccessTokenForClient(@PathVariable long memberId) {
		return authService.getAccessTokenForClient(memberId);
	}

	@PostMapping("/login")
	public ResponseEntity<SuccessResponse<UserAllTokenGetResponse>> login(
			HttpServletResponse response,
			@RequestBody LoginRequest userInfo){
		val token = authService.login(userInfo,response);
		return ResponseEntity.created(UriGenerator.getUri("/"))
				.body(SuccessResponse.success(SUCCESS_SIGN_IN.getMessage(), token));
	}

	@GetMapping("/reissue")
	public ResponseEntity<SuccessResponse<UserTokenGetResponse>> reissue(HttpServletRequest request){
		val response = authService.reissue(request);
		return ResponseEntity.created(UriGenerator.getUri("/"))
				.body(SuccessResponse.success(SUCCESS_GENERATE_ACCESS_TOKEN.getMessage(), response));
	}
}
