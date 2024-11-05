package com.tiki.server.note.controller;

import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.common.support.UriGenerator;
import com.tiki.server.note.controller.dto.request.NoteFreeCreateRequest;
import com.tiki.server.note.controller.dto.request.NoteTemplateCreateRequest;
import com.tiki.server.note.service.NoteService;
import com.tiki.server.note.service.dto.request.NoteFreeCreateDTO;
import com.tiki.server.note.service.dto.request.NoteTemplateCreateDTO;
import com.tiki.server.note.service.dto.response.NoteCreateResponseDTO;
import com.tiki.server.note.service.dto.response.NoteGetResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static com.tiki.server.common.dto.SuccessResponse.success;
import static com.tiki.server.note.message.SuccessMessage.CREATE_NOTE_FREE;
import static com.tiki.server.note.message.SuccessMessage.GET_NOTE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notes")
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/free")
    public ResponseEntity<SuccessResponse<NoteCreateResponseDTO>> createNoteFree(
            final Principal principal,
            @RequestBody final NoteFreeCreateRequest request
    ) {
        long memberId = Long.parseLong(principal.getName());
        NoteCreateResponseDTO response = noteService.createNoteFree(NoteFreeCreateDTO.of(request, memberId));
        return ResponseEntity.created(
                UriGenerator.getUri("/api/v1/notes/free" + response.noteId())
        ).body(success(CREATE_NOTE_FREE.getMessage(), response));
    }

    @PostMapping("/template")
    public ResponseEntity<SuccessResponse<NoteCreateResponseDTO>> createNoteTemplate(
            final Principal principal,
            @RequestBody final NoteTemplateCreateRequest request
    ) {
        long memberId = Long.parseLong(principal.getName());
        NoteCreateResponseDTO response = noteService.createNoteTemplate(NoteTemplateCreateDTO.of(request, memberId));
        return ResponseEntity.created(
                UriGenerator.getUri("/api/v1/notes/free" + response.noteId())
        ).body(success(CREATE_NOTE_FREE.getMessage(), response));
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<SuccessResponse<NoteGetResponseDTO>> getNote(
            final Principal principal,
            @PathVariable long teamId
    ){
        long memberId = Long.parseLong(principal.getName());
        NoteGetResponseDTO response = noteService.getNote(teamId,memberId);
        return ResponseEntity.ok().body(success(GET_NOTE.getMessage(), response));
    }
}