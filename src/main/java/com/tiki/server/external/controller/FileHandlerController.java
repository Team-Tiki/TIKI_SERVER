package com.tiki.server.external.controller;

import static com.tiki.server.external.message.SuccessMessage.PRESIGNED_URL_GET_SUCCESS;
import static com.tiki.server.external.message.SuccessMessage.S3_FILE_DELETE_SUCCESS;

import com.tiki.server.external.service.FileHandlerService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.external.controller.docs.FileHandlerControllerDocs;
import com.tiki.server.external.dto.request.S3DeleteRequest;
import com.tiki.server.external.dto.response.PreSignedUrlResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/file")
public class FileHandlerController implements FileHandlerControllerDocs {

	private final FileHandlerService fileHandlerService;

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/upload")
	public SuccessResponse<PreSignedUrlResponse> getPreSignedUrl(
		@RequestParam final String fileFormat) {
		PreSignedUrlResponse response = fileHandlerService.getUploadPreSignedUrl(fileFormat);
		return SuccessResponse.success(PRESIGNED_URL_GET_SUCCESS.getMessage(), response);
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@PostMapping
	public SuccessResponse<?> deleteFile(@RequestBody final S3DeleteRequest request) {
		fileHandlerService.deleteFile(request);
		return SuccessResponse.success(S3_FILE_DELETE_SUCCESS.getMessage());
	}
}
