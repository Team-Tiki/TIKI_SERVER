package com.tiki.server.note.controller;

import com.tiki.server.common.dto.BaseResponse;
import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.common.entity.SortOrder;
import com.tiki.server.common.support.UriGenerator;
import com.tiki.server.note.controller.dto.request.NoteFreeCreateRequest;
import com.tiki.server.note.controller.dto.request.NoteFreeUpdateRequest;
import com.tiki.server.note.controller.dto.request.NoteTemplateCreateRequest;
import com.tiki.server.note.controller.dto.request.NoteTemplateUpdateRequest;
import com.tiki.server.note.service.NoteService;
import com.tiki.server.note.service.dto.request.NoteFreeCreateServiceRequest;
import com.tiki.server.note.service.dto.request.NoteFreeUpdateServiceRequest;
import com.tiki.server.note.service.dto.request.NoteTemplateCreateServiceRequest;
import com.tiki.server.note.service.dto.request.NoteTemplateUpdateServiceRequest;
import com.tiki.server.note.service.dto.response.NoteCreateServiceResponse;
import com.tiki.server.note.service.dto.response.NoteDetailGetServiceResponse;
import com.tiki.server.note.service.dto.response.NoteListGetServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import static com.tiki.server.common.dto.SuccessResponse.success;
import static com.tiki.server.note.message.SuccessMessage.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notes")
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/free")
    public ResponseEntity<SuccessResponse<NoteCreateServiceResponse>> createNoteFree(
            final Principal principal,
            @RequestBody final NoteFreeCreateRequest request
    ) {
        long memberId = Long.parseLong(principal.getName());
        NoteCreateServiceResponse response = noteService.createNoteFree(NoteFreeCreateServiceRequest.of(request, memberId));
        return ResponseEntity.created(
                UriGenerator.getUri("/api/v1/notes" + response.noteId())
        ).body(success(CREATE_NOTE.getMessage(), response));
    }

    @PostMapping("/template")
    public ResponseEntity<SuccessResponse<NoteCreateServiceResponse>> createNoteTemplate(
            final Principal principal,
            @RequestBody final NoteTemplateCreateRequest request
    ) {
        long memberId = Long.parseLong(principal.getName());
        NoteCreateServiceResponse response = noteService.createNoteTemplate(NoteTemplateCreateServiceRequest.of(request, memberId));
        return ResponseEntity.created(
                UriGenerator.getUri("/api/v1/notes" + response.noteId())
        ).body(success(CREATE_NOTE.getMessage(), response));
    }

    @PatchMapping("/free/{noteId}")
    public ResponseEntity<BaseResponse> updateNoteFree(
            final Principal principal,
            @PathVariable final long noteId,
            @RequestBody final NoteFreeUpdateRequest request
    ) {
        long memberId = Long.parseLong(principal.getName());
        noteService.updateNoteFree(NoteFreeUpdateServiceRequest.of(request, noteId, memberId));
        return ResponseEntity.ok().body(success(UPDATE_NOTE.getMessage()));
    }

    @PatchMapping("/template/{noteId}")
    public ResponseEntity<BaseResponse> updateNoteTemplate(
            final Principal principal,
            @PathVariable final long noteId,
            @RequestBody final NoteTemplateUpdateRequest request
    ) {
        long memberId = Long.parseLong(principal.getName());
        noteService.updateNoteTemplate(NoteTemplateUpdateServiceRequest.of(request, noteId, memberId));
        return ResponseEntity.ok().body(success(UPDATE_NOTE.getMessage()));
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<SuccessResponse<NoteListGetServiceResponse>> getNote(
            final Principal principal,
            @PathVariable long teamId,
            @RequestParam(required = false) LocalDateTime createdAt,
            @RequestParam(defaultValue = "DESC") SortOrder sortOrder
    ) {
        long memberId = Long.parseLong(principal.getName());
        if (createdAt == null) {
            createdAt = (sortOrder == SortOrder.DESC) ? LocalDateTime.now() : LocalDateTime.of(1970, 1, 1, 0, 0);
        }
        NoteListGetServiceResponse response = noteService.getNote(teamId, memberId, createdAt, sortOrder);
        return ResponseEntity.ok().body(success(GET_NOTE.getMessage(), response));
    }

    @GetMapping("/{teamId}/{noteId}")
    public ResponseEntity<SuccessResponse<NoteDetailGetServiceResponse>> getNoteDetail(
            final Principal principal,
            @PathVariable final long teamId,
            @PathVariable final long noteId
    ) {
        long memberId = Long.parseLong(principal.getName());
        NoteDetailGetServiceResponse response = noteService.getNoteDetail(teamId, memberId, noteId);
        return ResponseEntity.ok().body(success(GET_NOTE_DETAIL.getMessage(), response));
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<SuccessResponse<Void>> deleteNotes(
            final Principal principal,
            @PathVariable long teamId,
            @RequestParam List<Long> noteIds
    ) {
        long memberId = Long.parseLong(principal.getName());
        noteService.deleteNotes(noteIds, teamId, memberId);
        return ResponseEntity.noContent().build();
    }
}