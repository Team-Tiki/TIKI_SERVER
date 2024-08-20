package com.tiki.server.external.controller;

import static com.tiki.server.common.dto.SuccessResponse.*;
import static com.tiki.server.external.message.SuccessMessage.PRESIGNED_URL_GET_SUCCESS;
import static com.tiki.server.external.message.SuccessMessage.S3_FILE_DELETE_SUCCESS;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tiki.server.common.dto.BaseResponse;
import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.external.controller.docs.S3ControllerDocs;
import com.tiki.server.external.dto.request.S3DeleteRequest;
import com.tiki.server.external.dto.response.PreSignedUrlResponse;
import com.tiki.server.external.util.S3Service;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/file")
public class S3Controller implements S3ControllerDocs {

	private final S3Service s3Service;

	@Override
	@GetMapping("/upload")
	public ResponseEntity<SuccessResponse<PreSignedUrlResponse>> getPreSignedUrl(@RequestParam String fileFormat) {
		val response = s3Service.getUploadPreSignedUrl(fileFormat);
		return ResponseEntity.ok(success(PRESIGNED_URL_GET_SUCCESS.getMessage(), response));
	}

	@Override
	@PostMapping
	public ResponseEntity<BaseResponse> deleteFile(@RequestBody S3DeleteRequest request) {
		s3Service.deleteFile(request);
		return ResponseEntity.ok(success(S3_FILE_DELETE_SUCCESS.getMessage()));
	}
}
