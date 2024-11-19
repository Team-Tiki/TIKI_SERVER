package com.tiki.server.folder.controller;

import static com.tiki.server.common.dto.SuccessResponse.*;
import static com.tiki.server.folder.constant.Constant.ROOT_PATH;
import static com.tiki.server.folder.message.SuccessMessage.SUCCESS_CREATE_FOLDER;
import static com.tiki.server.folder.message.SuccessMessage.SUCCESS_GET_FOLDERS;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.common.support.UriGenerator;
import com.tiki.server.folder.dto.request.FolderCreateRequest;
import com.tiki.server.folder.dto.response.FolderCreateResponse;
import com.tiki.server.folder.dto.response.FoldersGetResponse;
import com.tiki.server.folder.service.FolderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class FolderController {

	private final FolderService folderService;

	@GetMapping("/teams/{teamId}/folders")
	public ResponseEntity<SuccessResponse<FoldersGetResponse>> getFolders(
		final Principal principal,
		@RequestHeader("team-id") long teamId,
		@RequestParam(defaultValue = ROOT_PATH) String path
	) {
		long memberId = Long.parseLong(principal.getName());
		FoldersGetResponse response = folderService.get(memberId, teamId, path);
		return ResponseEntity.ok(success(SUCCESS_GET_FOLDERS.getMessage(), response));
	}

	@PostMapping("/teams/{teamId}/folders")
	public ResponseEntity<SuccessResponse<FolderCreateResponse>> createFolder(
		Principal principal,
		@RequestHeader("team-id") long teamId,
		@RequestBody FolderCreateRequest request
	) {
		long memberId = Long.parseLong(principal.getName());
		FolderCreateResponse response = folderService.create(memberId, teamId, request);
		return ResponseEntity.created(UriGenerator.getUri("api/v1/folders/" + response.folderId()))
			.body(success(SUCCESS_CREATE_FOLDER.getMessage(), response));
	}
}
