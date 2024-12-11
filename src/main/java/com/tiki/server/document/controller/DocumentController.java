package com.tiki.server.document.controller;

import static com.tiki.server.document.message.SuccessMessage.SUCCESS_CREATE_DOCUMENTS;
import static com.tiki.server.document.message.SuccessMessage.SUCCESS_GET_DOCUMENTS;
import static com.tiki.server.document.message.SuccessMessage.SUCCESS_GET_TRASH;

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
import com.tiki.server.document.controller.docs.DocumentControllerDocs;
import com.tiki.server.document.dto.request.DocumentsCreateRequest;
import com.tiki.server.document.dto.response.DeletedDocumentsGetResponse;
import com.tiki.server.document.dto.response.DocumentsGetResponse;
import com.tiki.server.document.service.DocumentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class DocumentController implements DocumentControllerDocs {

	private final DocumentService documentService;

	@Override
	@GetMapping("/documents/team/{teamId}/timeline")
	public ResponseEntity<SuccessResponse<DocumentsGetResponse>> getAllDocuments(
		final Principal principal,
		@PathVariable final long teamId,
		@RequestParam final String type
	) {
		long memberId = Long.parseLong(principal.getName());
		DocumentsGetResponse response = documentService.getAllDocuments(memberId, teamId, type);
		return ResponseEntity.ok(SuccessResponse.success(SUCCESS_GET_DOCUMENTS.getMessage(), response));
	}

	@PostMapping("/teams/{teamId}/documents")
	public ResponseEntity<SuccessResponse<?>> createDocuments(
		final Principal principal,
		@PathVariable final long teamId,
		@RequestParam(required = false) final Long folderId,
		@RequestBody final DocumentsCreateRequest request
	) {
		long memberId = Long.parseLong(principal.getName());
		documentService.createDocuments(memberId, teamId, folderId, request);
		return ResponseEntity.created(UriGenerator.getUri("teams/" + teamId + "/documents"))
			.body(SuccessResponse.success(SUCCESS_CREATE_DOCUMENTS.getMessage()));
	}

	@GetMapping("/teams/{teamId}/documents")
	public ResponseEntity<SuccessResponse<DocumentsGetResponse>> getDocuments(
		final Principal principal,
		@PathVariable final long teamId,
		@RequestParam(required = false) final Long folderId
	) {
		long memberId = Long.parseLong(principal.getName());
		DocumentsGetResponse response = documentService.get(memberId, teamId, folderId);
		return ResponseEntity.ok(SuccessResponse.success(SUCCESS_GET_DOCUMENTS.getMessage(), response));
	}

	@DeleteMapping("/teams/{teamId}/documents")
	public ResponseEntity<?> delete(
		final Principal principal,
		@PathVariable final long teamId,
		@RequestParam("documentId") final List<Long> documentIds
	) {
		long memberId = Long.parseLong(principal.getName());
		documentService.delete(memberId, teamId, documentIds);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/teams/{teamId}/trash")
	public ResponseEntity<?> deleteTrash(
		final Principal principal,
		@PathVariable final long teamId,
		@RequestParam("documentId") final List<Long> deletedDocumentIds
	) {
		long memberId = Long.parseLong(principal.getName());
		documentService.deleteTrash(memberId, teamId, deletedDocumentIds);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/teams/{teamId}/trash")
	public ResponseEntity<?> restore(
		final Principal principal,
		@PathVariable final long teamId,
		@RequestParam("documentId") final List<Long> deletedDocumentIds
	) {
		long memberId = Long.parseLong(principal.getName());
		documentService.restore(memberId, teamId, deletedDocumentIds);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/teams/{teamId}/trash")
	public ResponseEntity<SuccessResponse<DeletedDocumentsGetResponse>> getTrash(
		final Principal principal,
		@PathVariable final long teamId
	) {
		long memberId = Long.parseLong(principal.getName());
		DeletedDocumentsGetResponse response = documentService.getTrash(memberId, teamId);
		return ResponseEntity.ok(SuccessResponse.success(SUCCESS_GET_TRASH.getMessage(), response));
	}
}
