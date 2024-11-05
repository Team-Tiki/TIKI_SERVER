package com.tiki.server.note.service;

import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import com.tiki.server.note.adapter.NoteSaver;
import com.tiki.server.note.entity.Note;
import com.tiki.server.note.service.dto.request.NoteCreateDTO;
import com.tiki.server.note.service.dto.response.NoteCreateResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final MemberTeamManagerFinder memberTeamManagerFinder;
    private final NoteSaver noteFreeSaver;

    public NoteCreateResponseDTO createNoteFree(final NoteCreateDTO request) {
        memberTeamManagerFinder.findByMemberIdAndTeamId(request.memberId(), request.teamId());
        Note note = noteFreeSaver.createNoteFree(
                Note.of(
                        request.title(),
                        request.complete(),
                        request.startDate(),
                        request.endDate(),
                        request.contents(),
                        request.memberId(),
                        request.teamId()
                ));
        return NoteCreateResponseDTO.from(note.getId());
    }
}