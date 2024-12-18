package com.tiki.server.drive.controller;

import static com.tiki.server.common.dto.SuccessResponse.success;
import static com.tiki.server.drive.message.SuccessMessage.SUCCESS_GET_DRIVE;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.drive.controller.docs.DriveControllerDocs;
import com.tiki.server.drive.dto.DriveGetResponse;
import com.tiki.server.drive.service.DriveService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class DriveController implements DriveControllerDocs {

	private final DriveService driveService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/teams/{teamId}/drive")
	public SuccessResponse<DriveGetResponse> getDrive(
		final Principal principal,
		@PathVariable final long teamId,
		@RequestParam(required = false) final Long folderId
	) {
		long memberId = Long.parseLong(principal.getName());
		DriveGetResponse response = driveService.getDrive(memberId, teamId, folderId);
		return success(SUCCESS_GET_DRIVE.getMessage(), response);
	}
}
