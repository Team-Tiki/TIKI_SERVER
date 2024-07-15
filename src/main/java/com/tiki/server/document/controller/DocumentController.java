package com.tiki.server.document.controller;

import static com.tiki.server.document.message.SuccessMessage.SUCCESS_GET_DOCUMENTS;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.document.dto.response.DocumentsGetResponse;
import com.tiki.server.document.service.DocumentService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/documents")
public class DocumentController {

	private final DocumentService documentService;

	@GetMapping("/team/{teamId}/timeline")
	public ResponseEntity<SuccessResponse<DocumentsGetResponse>> getAllDocuments(
		Principal principal,
		@PathVariable long teamId,
		@RequestParam String type
	) {
		val memberId = Long.parseLong(principal.getName());
		val response = documentService.getAllDocuments(memberId, teamId, type);
		return ResponseEntity.ok(SuccessResponse.success(SUCCESS_GET_DOCUMENTS.getMessage(), response));
	}
}
