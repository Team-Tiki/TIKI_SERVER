package com.tiki.server.note.service;

import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import com.tiki.server.note.adapter.NoteFreeSaver;
import com.tiki.server.note.adapter.NoteTemplateSaver;
import com.tiki.server.note.entity.NoteFree;
import com.tiki.server.note.entity.NoteTemplate;
import com.tiki.server.note.service.dto.request.NoteFreeCreateDTO;
import com.tiki.server.note.service.dto.request.NoteTemplateCreateDTO;
import com.tiki.server.note.service.dto.response.NoteCreateResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final MemberTeamManagerFinder memberTeamManagerFinder;
    private final NoteFreeSaver noteFreeSaver;
    private final NoteTemplateSaver noteTemplateSaver;

    public NoteCreateResponseDTO createNoteFree(final NoteFreeCreateDTO request) {
        memberTeamManagerFinder.findByMemberIdAndTeamId(request.memberId(), request.teamId());
        NoteFree noteFree = noteFreeSaver.createNoteFree(
                NoteFree.of(
                        request.title(),
                        request.complete(),
                        request.startDate(),
                        request.endDate(),
                        request.contents(),
                        request.teamId()
                ));
        return NoteCreateResponseDTO.from(noteFree.getId());
    }

    public NoteCreateResponseDTO createNoteTemplate(final NoteTemplateCreateDTO request) {
        memberTeamManagerFinder.findByMemberIdAndTeamId(request.memberId(), request.teamId());
        NoteTemplate noteTemplate = noteTemplateSaver.createNoteTemplate(
                NoteTemplate.of(
                        request.title(),
                        request.complete(),
                        request.startDate(),
                        request.endDate(),
                        request.answerWhatActivity(),
                        request.answerHowToPrepare(),
                        request.answerWhatIsDisappointedThing(),
                        request.answerHowToFix(),
                        request.teamId()
                ));
        return NoteCreateResponseDTO.from(noteTemplate.getId());
    }
}