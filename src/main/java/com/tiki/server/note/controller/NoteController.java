package com.tiki.server.note.controller;

import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.common.entity.SortOrder;
import com.tiki.server.note.controller.docs.NoteControllerDocs;
import com.tiki.server.note.controller.dto.request.NoteFreeCreateRequest;
import com.tiki.server.note.controller.dto.request.NoteFreeUpdateRequest;
import com.tiki.server.note.controller.dto.request.NoteTemplateCreateRequest;
import com.tiki.server.note.controller.dto.request.NoteTemplateUpdateRequest;
import com.tiki.server.note.controller.dto.response.SuccessNoteDetailResponse;
import com.tiki.server.note.service.NoteService;
import com.tiki.server.note.service.dto.request.NoteFreeCreateServiceRequest;
import com.tiki.server.note.service.dto.request.NoteFreeUpdateServiceRequest;
import com.tiki.server.note.service.dto.request.NoteTemplateCreateServiceRequest;
import com.tiki.server.note.service.dto.request.NoteTemplateUpdateServiceRequest;
import com.tiki.server.note.service.dto.response.NoteCreateServiceResponse;
import com.tiki.server.note.service.dto.response.NoteDetailGetServiceResponse;
import com.tiki.server.note.service.dto.response.NoteListGetServiceResponse;
import lombok.RequiredArgsConstructor;

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

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import static com.tiki.server.note.message.SuccessMessage.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notes")
public class NoteController implements NoteControllerDocs {

    private final NoteService noteService;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/free")
    public SuccessResponse<NoteCreateServiceResponse> createNoteFree(
            final Principal principal,
            @RequestBody final NoteFreeCreateRequest request
    ) {
        long memberId = Long.parseLong(principal.getName());
        NoteCreateServiceResponse response = noteService.createNoteFree(NoteFreeCreateServiceRequest.of(request, memberId));
        return SuccessResponse.success(CREATE_NOTE.getMessage(), response);
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/template")
    public SuccessResponse<NoteCreateServiceResponse> createNoteTemplate(
            final Principal principal,
            @RequestBody final NoteTemplateCreateRequest request
    ) {
        long memberId = Long.parseLong(principal.getName());
        NoteCreateServiceResponse response = noteService.createNoteTemplate(NoteTemplateCreateServiceRequest.of(request, memberId));
        return SuccessResponse.success(CREATE_NOTE.getMessage(), response);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/free/{noteId}")
    public SuccessResponse<?> updateNoteFree(
            final Principal principal,
            @PathVariable final long noteId,
            @RequestBody final NoteFreeUpdateRequest request
    ) {
        long memberId = Long.parseLong(principal.getName());
        noteService.updateNoteFree(NoteFreeUpdateServiceRequest.of(request, noteId, memberId));
        return SuccessResponse.success(UPDATE_NOTE.getMessage());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/template/{noteId}")
    public SuccessResponse<?> updateNoteTemplate(
            final Principal principal,
            @PathVariable final long noteId,
            @RequestBody final NoteTemplateUpdateRequest request
    ) {
        long memberId = Long.parseLong(principal.getName());
        noteService.updateNoteTemplate(NoteTemplateUpdateServiceRequest.of(request, noteId, memberId));
        return SuccessResponse.success(UPDATE_NOTE.getMessage());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{teamId}")
    public SuccessResponse<NoteListGetServiceResponse> getNote(
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
        return SuccessResponse.success(GET_NOTE.getMessage(), response);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{teamId}/{noteId}")
    public SuccessNoteDetailResponse<NoteDetailGetServiceResponse> getNoteDetail(
            final Principal principal,
            @PathVariable final long teamId,
            @PathVariable final long noteId
    ) {
        long memberId = Long.parseLong(principal.getName());
        NoteDetailGetServiceResponse response = noteService.getNoteDetail(teamId, memberId, noteId);
        return SuccessNoteDetailResponse.success(GET_NOTE_DETAIL.getMessage(), response);
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{teamId}")
    public void deleteNotes(
            final Principal principal,
            @PathVariable final long teamId,
            @RequestParam final List<Long> noteIds
    ) {
        long memberId = Long.parseLong(principal.getName());
        noteService.deleteNotes(noteIds, teamId, memberId);
    }
}