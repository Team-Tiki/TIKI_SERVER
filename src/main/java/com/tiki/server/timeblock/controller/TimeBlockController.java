package com.tiki.server.timeblock.controller;

import static com.tiki.server.timeblock.message.SuccessMessage.SUCCESS_CREATE_DOCUMENT_TAG;
import static com.tiki.server.timeblock.message.SuccessMessage.SUCCESS_CREATE_TIME_BLOCK;
import static com.tiki.server.timeblock.message.SuccessMessage.SUCCESS_GET_ALL_TIME_BLOCK;
import static com.tiki.server.timeblock.message.SuccessMessage.SUCCESS_GET_TIMELINE;
import static com.tiki.server.timeblock.message.SuccessMessage.SUCCESS_GET_TIME_BLOCK_DETAIL;

import com.tiki.server.timeblock.service.dto.response.AllTimeBlockServiceResponse;
import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.timeblock.controller.docs.TimeBlockControllerDocs;
import com.tiki.server.timeblock.dto.request.TimeBlockCreateRequest;
import com.tiki.server.timeblock.dto.response.TimeBlockCreateResponse;
import com.tiki.server.timeblock.dto.response.TimeBlockDetailGetResponse;
import com.tiki.server.timeblock.dto.response.TimelineGetResponse;
import com.tiki.server.timeblock.service.TimeBlockService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class TimeBlockController implements TimeBlockControllerDocs {

	private final TimeBlockService timeBlockService;

	@Override
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/teams/{teamId}/time-block")
	public SuccessResponse<TimeBlockCreateResponse> createTimeBlock(
		final Principal principal,
		@PathVariable final long teamId,
		@RequestParam final String type,
		@RequestBody final TimeBlockCreateRequest request
	) {
		long memberId = Long.parseLong(principal.getName());
		TimeBlockCreateResponse response = timeBlockService.createTimeBlock(memberId, teamId, type, request);
		return SuccessResponse.success(SUCCESS_CREATE_TIME_BLOCK.getMessage(), response);
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/teams/{teamId}/timeline")
	public SuccessResponse<TimelineGetResponse> getTimeline(
		final Principal principal,
		@PathVariable final long teamId,
		@RequestParam final String type,
		@RequestParam final String date
	) {
		long memberId = Long.parseLong(principal.getName());
		TimelineGetResponse response = timeBlockService.getTimeline(memberId, teamId, type, date);
		return SuccessResponse.success(SUCCESS_GET_TIMELINE.getMessage(), response);
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/teams/{teamId}/time-block/all")
	public SuccessResponse<AllTimeBlockServiceResponse> getAllTimeBlock(
			final Principal principal,
			@PathVariable final long teamId
	) {
		long memberId = Long.parseLong(principal.getName());
		AllTimeBlockServiceResponse response = timeBlockService.getAllTimeBlock(memberId, teamId);
		return SuccessResponse.success(SUCCESS_GET_ALL_TIME_BLOCK.getMessage(), response);
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/teams/{teamId}/time-block/{timeBlockId}")
	public SuccessResponse<TimeBlockDetailGetResponse> getTimeBlockDetail(
		final Principal principal,
		@PathVariable final long teamId,
		@PathVariable final long timeBlockId
	) {
		long memberId = Long.parseLong(principal.getName());
		TimeBlockDetailGetResponse response = timeBlockService.getTimeBlockDetail(memberId, teamId, timeBlockId);
		return SuccessResponse.success(SUCCESS_GET_TIME_BLOCK_DETAIL.getMessage(), response);
	}

	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/teams/{teamId}/time-block/{timeBlockId}")
	public void deleteTimeBlock(
		final Principal principal,
		@PathVariable final long teamId,
		@PathVariable final long timeBlockId
	) {
		long memberId = Long.parseLong(principal.getName());
		timeBlockService.deleteTimeBlock(memberId, teamId, timeBlockId);
	}

	@Override
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/teams/{teamId}/time-block/{timeBlockId}")
	public SuccessResponse<?> createDocumentTag(
		final Principal principal,
		@PathVariable final long teamId,
		@PathVariable final long timeBlockId,
		@RequestParam("documentId") final List<Long> documentIds
	) {
		long memberId = Long.parseLong(principal.getName());
		timeBlockService.createDocumentTag(memberId, teamId, timeBlockId, documentIds);
		return SuccessResponse.success(SUCCESS_CREATE_DOCUMENT_TAG.getMessage());
	}

	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/teams/{teamId}/time-block/{timeBlockId}/tags")
	public void deleteDocumentTag(
		final Principal principal,
		@PathVariable final long teamId,
		@PathVariable final long timeBlockId,
		@RequestParam("tagId") final List<Long> tagIds
	) {
		long memberId = Long.parseLong(principal.getName());
		timeBlockService.deleteDocumentTag(memberId, teamId, timeBlockId, tagIds);
	}
}
