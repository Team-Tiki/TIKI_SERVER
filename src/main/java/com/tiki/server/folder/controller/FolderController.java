package com.tiki.server.folder.controller;

import static com.tiki.server.common.dto.SuccessResponse.*;
import static com.tiki.server.folder.message.SuccessMessage.SUCCESS_CREATE_FOLDER;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.common.support.UriGenerator;
import com.tiki.server.folder.dto.request.FolderCreateRequest;
import com.tiki.server.folder.dto.response.FolderCreateResponse;
import com.tiki.server.folder.service.FolderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/folders")
public class FolderController {

	private final FolderService folderService;

	@PostMapping()
	public ResponseEntity<SuccessResponse<FolderCreateResponse>> createFolder(
		Principal principal,
		@RequestHeader("team-id") long teamId,
		@RequestBody FolderCreateRequest request
	) {
		long memberId = Long.parseLong(principal.getName());
		FolderCreateResponse response = folderService.create(memberId, teamId, request);
		return ResponseEntity.created(UriGenerator.getUri("api/v2/folders/" + response.folderId()))
			.body(success(SUCCESS_CREATE_FOLDER.getMessage(), response));
	}
}
