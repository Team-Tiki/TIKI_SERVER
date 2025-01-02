package com.tiki.server.folder.controller;

import static com.tiki.server.folder.message.SuccessMessage.SUCCESS_CREATE_FOLDER;
import static com.tiki.server.folder.message.SuccessMessage.SUCCESS_GET_FOLDERS;
import static com.tiki.server.folder.message.SuccessMessage.SUCCESS_UPDATE_FOLDER_NAME;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.folder.controller.docs.FolderControllerDocs;
import com.tiki.server.folder.dto.request.FolderCreateRequest;
import com.tiki.server.folder.dto.request.FolderNameUpdateRequest;
import com.tiki.server.folder.dto.response.FolderCreateResponse;
import com.tiki.server.folder.dto.response.FoldersGetResponse;
import com.tiki.server.folder.service.FolderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class FolderController implements FolderControllerDocs {

	private final FolderService folderService;

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/teams/{teamId}/folders")
	public SuccessResponse<FoldersGetResponse> getFolders(
		final Principal principal,
		@PathVariable final long teamId,
		@RequestParam(required = false) final Long folderId
	) {
		long memberId = Long.parseLong(principal.getName());
		FoldersGetResponse response = folderService.get(memberId, teamId, folderId);
		return SuccessResponse.success(SUCCESS_GET_FOLDERS.getMessage(), response);
	}

	@Override
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/teams/{teamId}/folders")
	public SuccessResponse<FolderCreateResponse> createFolder(
		final Principal principal,
		@PathVariable final long teamId,
		@RequestParam(required = false) final Long folderId,
		@RequestBody final FolderCreateRequest request
	) {
		long memberId = Long.parseLong(principal.getName());
		FolderCreateResponse response = folderService.create(memberId, teamId, folderId, request);
		return SuccessResponse.success(SUCCESS_CREATE_FOLDER.getMessage(), response);
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@PatchMapping("/teams/{teamId}/folders/{folderId}")
	public SuccessResponse<?> updateFolderName(
		final Principal principal,
		@PathVariable final long teamId,
		@PathVariable final long folderId,
		@RequestBody final FolderNameUpdateRequest request
	) {
		long memberId = Long.parseLong(principal.getName());
		folderService.updateFolderName(memberId, teamId, folderId, request);
		return SuccessResponse.success(SUCCESS_UPDATE_FOLDER_NAME.getMessage());
	}

	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/teams/{teamId}/folders")
	public void delete(
		final Principal principal,
		@PathVariable final long teamId,
		@RequestParam("folderId") final List<Long> folderIds
	) {
		long memberId = Long.parseLong(principal.getName());
		folderService.delete(memberId, teamId, folderIds);
	}
}
