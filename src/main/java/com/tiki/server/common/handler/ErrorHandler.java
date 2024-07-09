package com.tiki.server.common.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tiki.server.common.dto.BaseResponse;
import com.tiki.server.common.dto.ErrorResponse;
import com.tiki.server.document.exception.DocumentException;
import com.tiki.server.member.exception.MemberException;
import com.tiki.server.memberteammanager.exception.MemberTeamManagerException;
import com.tiki.server.team.exception.TeamException;
import com.tiki.server.timeblock.exception.TimeBlockException;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(MemberException.class)
	public ResponseEntity<BaseResponse> memberException(MemberException exception) {
		log.error(exception.getMessage());
		val errorCode = exception.getErrorCode();
		return ResponseEntity.status(errorCode.getHttpStatus()).body(ErrorResponse.of(errorCode.getMessage()));
	}

	@ExceptionHandler(TeamException.class)
	public ResponseEntity<BaseResponse> teamException(TeamException exception) {
		log.error(exception.getMessage());
		val errorCode = exception.getErrorCode();
		return ResponseEntity.status(errorCode.getHttpStatus()).body(ErrorResponse.of(errorCode.getMessage()));
	}

	@ExceptionHandler(MemberTeamManagerException.class)
	public ResponseEntity<BaseResponse> memberTeamManagerException(MemberTeamManagerException exception) {
		log.error(exception.getMessage());
		val errorCode = exception.getErrorCode();
		return ResponseEntity.status(errorCode.getHttpStatus()).body(ErrorResponse.of(errorCode.getMessage()));
	}

	@ExceptionHandler(TimeBlockException.class)
	public ResponseEntity<BaseResponse> timeBlockException(TimeBlockException exception) {
		log.error(exception.getMessage());
		val errorCode = exception.getErrorCode();
		return ResponseEntity.status(errorCode.getHttpStatus()).body(ErrorResponse.of(errorCode.getMessage()));
	}

	@ExceptionHandler(DocumentException.class)
	public ResponseEntity<BaseResponse> documentException(DocumentException exception) {
		log.error(exception.getMessage());
		val errorCode = exception.getErrorCode();
		return ResponseEntity.status(errorCode.getHttpStatus()).body(ErrorResponse.of(errorCode.getMessage()));
	}
}
