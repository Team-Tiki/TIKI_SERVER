package com.tiki.server.note.controller;

import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.common.support.UriGenerator;
import com.tiki.server.note.controller.dto.request.NoteCreateRequest;
import com.tiki.server.note.service.NoteService;
import com.tiki.server.note.service.dto.request.NoteCreateDTO;
import com.tiki.server.note.service.dto.response.NoteCreateResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static com.tiki.server.common.dto.SuccessResponse.success;
import static com.tiki.server.note.message.SuccessMessage.CREATE_NOTE_FREE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notes")
public class NoteController {

    private final NoteService noteService;

    @PostMapping
    public ResponseEntity<SuccessResponse<NoteCreateResponseDTO>> createNote(
            final Principal principal,
            @RequestBody final NoteCreateRequest request
    ) {
        long memberId = Long.parseLong(principal.getName());
        NoteCreateResponseDTO response = noteService.createNoteFree(NoteCreateDTO.of(request, memberId));
        return ResponseEntity.created(
                UriGenerator.getUri("/api/v1/notes" + response.noteId())
        ).body(success(CREATE_NOTE_FREE.getMessage(), response));
    }
}