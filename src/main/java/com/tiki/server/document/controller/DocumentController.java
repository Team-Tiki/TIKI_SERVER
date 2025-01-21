package com.tiki.server.document.controller;

import static com.tiki.server.document.message.SuccessMessage.SUCCESS_CREATE_DOCUMENTS;
import static com.tiki.server.document.message.SuccessMessage.SUCCESS_GET_DOCUMENTS;
import static com.tiki.server.document.message.SuccessMessage.SUCCESS_GET_TRASH;

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
import com.tiki.server.document.controller.docs.DocumentControllerDocs;
import com.tiki.server.document.dto.request.DocumentsCreateRequest;
import com.tiki.server.document.dto.response.DeletedDocumentsGetResponse;
import com.tiki.server.document.dto.response.DocumentsCreateResponse;
import com.tiki.server.document.dto.response.DocumentsGetResponse;
import com.tiki.server.document.service.DocumentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class DocumentController implements DocumentControllerDocs {

	private final DocumentService documentService;

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/documents/team/{teamId}/timeline")
	public SuccessResponse<DocumentsGetResponse> getAllDocuments(
		final Principal principal,
		@PathVariable final long teamId,
		@RequestParam final String type
	) {
		long memberId = Long.parseLong(principal.getName());
		DocumentsGetResponse response = documentService.getAllDocuments(memberId, teamId, type);
		return SuccessResponse.success(SUCCESS_GET_DOCUMENTS.getMessage(), response);
	}

	@Override
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/teams/{teamId}/documents")
	public SuccessResponse<DocumentsCreateResponse> createDocuments(
		final Principal principal,
		@PathVariable final long teamId,
		@RequestParam(required = false) final Long folderId,
		@RequestBody final DocumentsCreateRequest request
	) {
		long memberId = Long.parseLong(principal.getName());
		DocumentsCreateResponse response = documentService.createDocuments(memberId, teamId, folderId, request);
		return SuccessResponse.success(SUCCESS_CREATE_DOCUMENTS.getMessage(), response);
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/teams/{teamId}/documents")
	public SuccessResponse<DocumentsGetResponse> getDocuments(
		final Principal principal,
		@PathVariable final long teamId,
		@RequestParam(required = false) final Long folderId
	) {
		long memberId = Long.parseLong(principal.getName());
		DocumentsGetResponse response = documentService.get(memberId, teamId, folderId);
		return SuccessResponse.success(SUCCESS_GET_DOCUMENTS.getMessage(), response);
	}

	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/teams/{teamId}/documents")
	public void delete(
		final Principal principal,
		@PathVariable final long teamId,
		@RequestParam("documentId") final List<Long> documentIds
	) {
		long memberId = Long.parseLong(principal.getName());
		documentService.delete(memberId, teamId, documentIds);
	}

	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/teams/{teamId}/trash")
	public void deleteTrash(
		final Principal principal,
		@PathVariable final long teamId,
		@RequestParam("documentId") final List<Long> deletedDocumentIds
	) {
		long memberId = Long.parseLong(principal.getName());
		documentService.deleteTrash(memberId, teamId, deletedDocumentIds);
	}

	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PostMapping("/teams/{teamId}/trash")
	public void restore(
		final Principal principal,
		@PathVariable final long teamId,
		@RequestParam("documentId") final List<Long> deletedDocumentIds
	) {
		long memberId = Long.parseLong(principal.getName());
		documentService.restore(memberId, teamId, deletedDocumentIds);
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/teams/{teamId}/trash")
	public SuccessResponse<DeletedDocumentsGetResponse> getTrash(
		final Principal principal,
		@PathVariable final long teamId
	) {
		long memberId = Long.parseLong(principal.getName());
		DeletedDocumentsGetResponse response = documentService.getTrash(memberId, teamId);
		return SuccessResponse.success(SUCCESS_GET_TRASH.getMessage(), response);
	}
}
