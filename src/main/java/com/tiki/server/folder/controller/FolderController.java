package com.tiki.server.folder.controller;

import static com.tiki.server.common.dto.SuccessResponse.*;
import static com.tiki.server.folder.message.SuccessMessage.SUCCESS_CREATE_FOLDER;
import static com.tiki.server.folder.message.SuccessMessage.SUCCESS_GET_FOLDERS;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		@PathVariable final long teamId,
		@RequestParam(required = false) final Long folderId
	) {
		long memberId = Long.parseLong(principal.getName());
		FoldersGetResponse response = folderService.get(memberId, teamId, folderId);
		return ResponseEntity.ok(success(SUCCESS_GET_FOLDERS.getMessage(), response));
	}

	@PostMapping("/teams/{teamId}/folders")
	public ResponseEntity<SuccessResponse<FolderCreateResponse>> createFolder(
		Principal principal,
		@PathVariable final long teamId,
		@RequestParam(required = false) final Long folderId,
		@RequestBody final FolderCreateRequest request
	) {
		long memberId = Long.parseLong(principal.getName());
		FolderCreateResponse response = folderService.create(memberId, teamId, folderId, request);
		return ResponseEntity.created(UriGenerator.getUri("api/v1/folders/" + response.folderId()))
			.body(success(SUCCESS_CREATE_FOLDER.getMessage(), response));
	}

	@DeleteMapping("/teams/{teamId}/folders")
	public ResponseEntity<?> delete(
		final Principal principal,
		@PathVariable final long teamId,
		@RequestParam final List<Long> folderIds
	) {
		long memberId = Long.parseLong(principal.getName());
		folderService.delete(memberId, teamId, folderIds);
		return ResponseEntity.noContent().build();
	}
}
