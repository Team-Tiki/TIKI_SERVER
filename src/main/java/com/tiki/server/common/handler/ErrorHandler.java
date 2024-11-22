package com.tiki.server.common.handler;

import com.tiki.server.auth.exception.AuthException;
import com.tiki.server.common.dto.ErrorCodeResponse;
import com.tiki.server.emailverification.exception.EmailVerificationException;
import com.tiki.server.folder.exception.FolderException;
import com.tiki.server.note.exception.NoteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tiki.server.common.dto.BaseResponse;
import com.tiki.server.common.dto.ErrorResponse;
import com.tiki.server.document.exception.DocumentException;
import com.tiki.server.external.exception.ExternalException;
import com.tiki.server.member.exception.MemberException;
import com.tiki.server.memberteammanager.exception.MemberTeamManagerException;
import com.tiki.server.team.exception.TeamException;
import com.tiki.server.timeblock.exception.TimeBlockException;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import static com.tiki.server.auth.message.ErrorCode.UNCAUGHT_SERVER_EXCEPTION;
import static com.tiki.server.common.Constants.WRONG_INPUT;

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

    @ExceptionHandler(NoteException.class)
    public ResponseEntity<BaseResponse> noteException(NoteException exception) {
        log.error(exception.getMessage());
        val errorCode = exception.getErrorCode();
        return ResponseEntity.status(errorCode.getHttpStatus()).body(ErrorResponse.of(errorCode.getMessage()));
    }

    @ExceptionHandler(ExternalException.class)
    public ResponseEntity<BaseResponse> externalException(ExternalException exception) {
        log.error(exception.getMessage());
        val errorCode = exception.getErrorCode();
        return ResponseEntity.status(errorCode.getHttpStatus()).body(ErrorResponse.of(errorCode.getMessage()));
    }

    @ExceptionHandler(EmailVerificationException.class)
    public ResponseEntity<BaseResponse> mailException(EmailVerificationException exception) {
        log.error(exception.getMessage());
        val errorCode = exception.getErrorCode();
        return ResponseEntity.status(errorCode.getHttpStatus()).body(ErrorResponse.of(errorCode.getMessage()));
    }

    @ExceptionHandler(FolderException.class)
    public ResponseEntity<BaseResponse> folderException(FolderException exception) {
        log.error(exception.getMessage());
        val errorCode = exception.getErrorCode();
        return ResponseEntity.status(errorCode.getHttpStatus()).body(ErrorResponse.of(errorCode.getMessage()));
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<BaseResponse> authException(AuthException exception) {
        log.error(exception.getMessage());
        val errorCode = exception.getErrorCode();
        return ResponseEntity.status(errorCode.getHttpStatus()).body(
                ErrorCodeResponse.of(errorCode.getCode(), errorCode.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BaseResponse> httpMessageNotReadableException(HttpMessageNotReadableException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorResponse.of(WRONG_INPUT));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse> exception(Exception exception) {
        log.error(exception.getMessage());
        val errorCode = UNCAUGHT_SERVER_EXCEPTION;
        return ResponseEntity.status(errorCode.getHttpStatus()).body(ErrorResponse.of(errorCode.getMessage()));
    }
}
