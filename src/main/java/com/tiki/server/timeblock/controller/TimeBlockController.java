package com.tiki.server.timeblock.controller;

import static com.tiki.server.common.dto.SuccessResponse.*;
import static com.tiki.server.timeblock.message.SuccessMessage.SUCCESS_CREATE_TIME_BLOCK;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.common.support.UriGenerator;
import com.tiki.server.timeblock.dto.request.TimeBlockCreationRequest;
import com.tiki.server.timeblock.dto.response.TimeBlockCreationResponse;
import com.tiki.server.timeblock.service.TimeBlockService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/time-blocks")
public class TimeBlockController {

	private final TimeBlockService timeBlockService;

	@PostMapping("/team/{teamId}/time-block")
	public ResponseEntity<SuccessResponse<TimeBlockCreationResponse>> createTimeBlock(
		Principal principal,
		@PathVariable("teamId") long teamId,
		@RequestParam String type,
		@RequestBody TimeBlockCreationRequest request
	) {
		val memberId = Long.parseLong(principal.getName());
		val response = timeBlockService.createTimeBlock(memberId, teamId, type, request);
		return ResponseEntity.created(
			UriGenerator.getUri("/api/v1/time-blocks/team/" + teamId + "/time-block")
		).body(success(SUCCESS_CREATE_TIME_BLOCK.getMessage(), response));
	}
}
