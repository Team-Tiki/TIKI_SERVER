package com.tiki.server.external.controller;

import static com.tiki.server.common.dto.SuccessResponse.*;
import static com.tiki.server.external.message.SuccessMessage.PRESIGNED_URL_GET_SUCCESS;
import static com.tiki.server.external.message.SuccessMessage.S3_FILE_DELETE_SUCCESS;

import com.tiki.server.external.service.FileHandlerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tiki.server.common.dto.BaseResponse;
import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.external.controller.docs.FileeHandlerControllerDocs;
import com.tiki.server.external.dto.request.S3DeleteRequest;
import com.tiki.server.external.dto.response.PreSignedUrlResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/file")
public class FileHandlerController implements FileeHandlerControllerDocs {

	private final FileHandlerService fileHandlerService;

	@Override
	@GetMapping("/upload")
	public ResponseEntity<SuccessResponse<PreSignedUrlResponse>> getPreSignedUrl(@RequestParam String fileFormat) {
		PreSignedUrlResponse response = fileHandlerService.getUploadPreSignedUrl(fileFormat);
		return ResponseEntity.ok(success(PRESIGNED_URL_GET_SUCCESS.getMessage(), response));
	}

	@Override
	@PostMapping
	public ResponseEntity<BaseResponse> deleteFile(@RequestBody S3DeleteRequest request) {
		fileHandlerService.deleteFile(request);
		return ResponseEntity.ok(success(S3_FILE_DELETE_SUCCESS.getMessage()));
	}
}
