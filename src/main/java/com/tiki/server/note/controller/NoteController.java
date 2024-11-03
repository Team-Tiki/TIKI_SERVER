package com.tiki.server.note.controller;

import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.common.support.UriGenerator;
import com.tiki.server.note.dto.request.NoteFreeCreateRequest;
import com.tiki.server.note.dto.request.NoteTemplateCreateRequest;
import com.tiki.server.note.dto.response.NoteCreateResponse;
import com.tiki.server.note.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static com.tiki.server.common.dto.SuccessResponse.success;
import static com.tiki.server.note.message.SuccessMessage.CREATE_NOTE_FREE;
import static com.tiki.server.team.message.SuccessMessage.SUCCESS_CREATE_TEAM;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notes")
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/free")
    public ResponseEntity<SuccessResponse<NoteCreateResponse>> createNoteFree(
            final Principal principal,
            @RequestBody final NoteFreeCreateRequest request
    ) {
        long memberId = Long.parseLong(principal.getName());
        NoteCreateResponse response = noteService.createNoteFree(memberId, request);
        return ResponseEntity.created(
                UriGenerator.getUri("/api/v1/notes/free" + response.noteId())
        ).body(success(CREATE_NOTE_FREE.getMessage(), response));
    }

    @PostMapping("/template")
    public ResponseEntity<SuccessResponse<NoteCreateResponse>> createNoteTemplate(
            final Principal principal,
            @RequestBody final NoteTemplateCreateRequest request
    ) {
        long memberId = Long.parseLong(principal.getName());
        NoteCreateResponse response = noteService.createNoteTemplate(memberId, request);
        return ResponseEntity.created(
                UriGenerator.getUri("/api/v1/notes/free" + response.noteId())
        ).body(success(CREATE_NOTE_FREE.getMessage(), response));
    }
}