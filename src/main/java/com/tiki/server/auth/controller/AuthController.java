package com.tiki.server.auth.controller;

import com.tiki.server.auth.dto.request.LoginRequest;
import com.tiki.server.auth.dto.response.LoginResponse;
import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.common.support.UriGenerator;
import com.tiki.server.team.dto.response.TeamCreateResponse;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tiki.server.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

import static com.tiki.server.auth.message.SuccessMessage.SUCCESS_SIGN_IN;
import static com.tiki.server.team.message.SuccessMessage.SUCCESS_CREATE_TEAM;

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
	public ResponseEntity<SuccessResponse<LoginResponse>> login(@RequestBody LoginRequest request){
		val response = authService.login(request);
		return ResponseEntity.created(UriGenerator.getUri("/"))
				.body(SuccessResponse.success(SUCCESS_SIGN_IN.getMessage(), response));
	}
}
