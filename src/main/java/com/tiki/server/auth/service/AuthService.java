package com.tiki.server.auth.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiki.server.auth.jwt.JwtGenerator;
import com.tiki.server.auth.jwt.UserAuthentication;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

	private final JwtGenerator jwtGenerator;

	public String getAccessTokenForClient(long memberId) {
		val authentication = new UserAuthentication(memberId, null, null);
		return jwtGenerator.generateToken(authentication, 12096000L);
	}
}
