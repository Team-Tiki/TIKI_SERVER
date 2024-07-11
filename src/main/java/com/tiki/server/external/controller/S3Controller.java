package com.tiki.server.external.controller;

import static com.tiki.server.common.dto.SuccessResponse.*;
import static com.tiki.server.external.message.SuccessMessage.PRESIGNED_URL_GET_SUCCESS;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.external.dto.request.PreSignedUrlRequest;
import com.tiki.server.external.dto.response.PreSignedUrlResponse;
import com.tiki.server.external.util.S3Service;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequestMapping("api/v1/file")
@RequiredArgsConstructor
public class S3Controller {

	private final S3Service s3Service;

	@GetMapping("/upload")
	public ResponseEntity<SuccessResponse<PreSignedUrlResponse>> getPreSignedUrl(@RequestBody PreSignedUrlRequest request) {
		val response = s3Service.getUploadPreSignedUrl(request);
		return ResponseEntity.ok(success(PRESIGNED_URL_GET_SUCCESS.getMessage(), response));
	}
}
