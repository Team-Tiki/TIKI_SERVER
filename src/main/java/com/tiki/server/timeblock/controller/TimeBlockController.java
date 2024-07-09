package com.tiki.server.timeblock.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<TimeBlockCreationResponse> createTimeBlock(
		Principal principal,
		@PathVariable("teamId") long teamId,
		@RequestParam String type,
		@RequestBody
	) {
		val memberId = Long.valueOf(principal.getName());
		val response = timeBlockService.createTimeBlock()
		return ResponseEntity.created(URI);
	}
}
