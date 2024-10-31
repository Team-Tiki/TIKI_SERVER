package com.tiki.server.note.service;

import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import com.tiki.server.note.adapter.NoteFreeSaver;
import com.tiki.server.note.adapter.NoteTemplateSaver;
import com.tiki.server.note.dto.request.NoteFreeCreateRequest;
import com.tiki.server.note.dto.request.NoteTemplateCreateRequest;
import com.tiki.server.note.dto.response.NoteCreateResponse;
import com.tiki.server.note.entity.NoteFree;
import com.tiki.server.note.entity.NoteTemplate;
import com.tiki.server.team.adapter.TeamFinder;
import com.tiki.server.team.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteService {

    MemberTeamManagerFinder memberTeamManagerFinder;
    NoteFreeSaver noteFreeSaver;
    NoteTemplateSaver noteTemplateSaver;
    TeamFinder teamFinder;
    public NoteCreateResponse createNoteFree(long memberId, NoteFreeCreateRequest request){
        memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, request.teamId());
        Team team = teamFinder.findById(request.teamId());
        NoteFree noteFree = noteFreeSaver.createNoteFree(
                NoteFree.of(
                        request.title(),
                        request.complete(),
                        request.startDate(),
                        request.endDate(),
                        request.contents(),
                        team
                ));
        return NoteCreateResponse.from(noteFree.getId());
    }

    public NoteCreateResponse createNoteTemplate(long memberId, NoteTemplateCreateRequest request){
        memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, request.teamId());
        Team team = teamFinder.findById(request.teamId());
        NoteTemplate noteTemplate = noteTemplateSaver.createNoteTemplate(
                NoteTemplate.of(
                        request.title(),
                        request.complete(),
                        request.startDate(),
                        request.endDate(),
                        request.answerWhatIsDisappointedThing(),
                        request.answerHowToPrepare(),
                        request.answerWhatIsDisappointedThing(),
                        request.answerHowToFix(),
                        team
                ));
        return NoteCreateResponse.from(noteTemplate.getId());
    }
}
