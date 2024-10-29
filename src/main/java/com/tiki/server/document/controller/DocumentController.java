package com.tiki.server.document.controller;

import static com.tiki.server.document.message.SuccessMessage.SUCCESS_CREATE_DOCUMENTS;
import static com.tiki.server.document.message.SuccessMessage.SUCCESS_GET_DOCUMENTS;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.common.support.UriGenerator;
import com.tiki.server.document.controller.docs.DocumentControllerDocs;
import com.tiki.server.document.dto.request.DocumentsCreateRequest;
import com.tiki.server.document.dto.response.DocumentCreateResponse;
import com.tiki.server.document.dto.response.DocumentsGetResponse;
import com.tiki.server.document.service.DocumentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/documents")
public class DocumentController implements DocumentControllerDocs {

	private final DocumentService documentService;

	@Override
	@GetMapping("/team/{teamId}/timeline")
	public ResponseEntity<SuccessResponse<DocumentsGetResponse>> getAllDocuments(
		Principal principal,
		@PathVariable long teamId,
		@RequestParam String type
	) {
		long memberId = Long.parseLong(principal.getName());
		DocumentsGetResponse response = documentService.getAllDocuments(memberId, teamId, type);
		return ResponseEntity.ok(SuccessResponse.success(SUCCESS_GET_DOCUMENTS.getMessage(), response));
	}

	@Override
	@DeleteMapping("/team/{teamId}/document/{documentId}")
	public ResponseEntity<?> deleteDocument(
		Principal principal,
		@PathVariable long teamId,
		@PathVariable long documentId
	) {
		long memberId = Long.parseLong(principal.getName());
		documentService.deleteDocument(memberId, teamId, documentId);
		return ResponseEntity.noContent().build();
	}

	@PostMapping
	public ResponseEntity<SuccessResponse<DocumentCreateResponse>> createDocuments(
		Principal principal,
		@RequestHeader("team-id") long teamId,
		@RequestBody DocumentsCreateRequest request
	) {
		long memberId = Long.parseLong(principal.getName());
		DocumentCreateResponse response = documentService.createDocuments(memberId, teamId, request);
		return ResponseEntity.created(UriGenerator.getUri("api/v1/documents"))
			.body(SuccessResponse.success(SUCCESS_CREATE_DOCUMENTS.getMessage(), response));
	}
}
