package com.tiki.server.auth.service;

import com.tiki.server.auth.dto.request.LoginRequest;
import com.tiki.server.auth.dto.response.LoginResponse;
import com.tiki.server.member.adapter.MemberFinder;
import com.tiki.server.member.entity.Member;
import com.tiki.server.member.exception.MemberException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiki.server.auth.jwt.JwtGenerator;
import com.tiki.server.auth.jwt.UserAuthentication;

import lombok.RequiredArgsConstructor;
import lombok.val;

import static com.tiki.server.member.message.ErrorCode.INVALID_MEMBER;
import static com.tiki.server.member.message.ErrorCode.UNMATCHED_PASSWORD;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

	private final JwtGenerator jwtGenerator;
	private final MemberFinder memberFinder;

	public String getAccessTokenForClient(long memberId) {
		val authentication = new UserAuthentication(memberId, null, null);
		return jwtGenerator.generateToken(authentication, 12096000L);
	}
	public LoginResponse login(LoginRequest request){
		val member = checkMemberEmpty(request);
		checkPasswordMatching(member,request.password());
		val authentication = createAuthentication(member.getId());
		return jwtGenerator.generateAllToken(authentication);
	}
	private Member checkMemberEmpty(LoginRequest request) {
		return memberFinder.findByEmail(request.email()).orElseThrow(()-> new MemberException(INVALID_MEMBER));

	}

	private void  checkPasswordMatching(Member member,String password){
		if(!member.getPassword().equals(password)){
			throw new MemberException(UNMATCHED_PASSWORD);
		}
	}
	private Authentication createAuthentication(long memberId){
		return new UserAuthentication(memberId, null, null);
	}
}
