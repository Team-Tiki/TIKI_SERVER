package com.tiki.server.note.service;

import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import com.tiki.server.note.adapter.NoteFreeSaver;
import com.tiki.server.note.adapter.NoteTemplateSaver;
import com.tiki.server.note.dto.request.NoteFreeCreateRequest;
import com.tiki.server.note.dto.request.NoteTemplateCreateRequest;
import com.tiki.server.note.dto.response.NoteCreateResponse;
import com.tiki.server.note.entity.NoteFree;
import com.tiki.server.note.entity.NoteTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final MemberTeamManagerFinder memberTeamManagerFinder;
    private final NoteFreeSaver noteFreeSaver;
    private final NoteTemplateSaver noteTemplateSaver;

    public NoteCreateResponse createNoteFree(final long memberId, final NoteFreeCreateRequest request) {
        memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, request.teamId());
        NoteFree noteFree = noteFreeSaver.createNoteFree(
                NoteFree.of(
                        request.title(),
                        request.complete(),
                        request.startDate(),
                        request.endDate(),
                        request.contents(),
                        request.teamId()
                ));
        return NoteCreateResponse.from(noteFree.getId());
    }

    public NoteCreateResponse createNoteTemplate(long memberId, NoteTemplateCreateRequest request) {
        memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, request.teamId());
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
        return NoteCreateResponse.from(noteTemplate.getId());
    }
}