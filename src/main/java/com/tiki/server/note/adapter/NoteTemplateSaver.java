package com.tiki.server.note.adapter;

import com.tiki.server.common.support.RepositoryAdapter;

import com.tiki.server.note.entity.NoteTemplate;
import com.tiki.server.note.repository.NoteTemplateRepository;
import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class NoteTemplateSaver {

    private final NoteTemplateRepository noteTemplateRepository;

    public NoteTemplate createNoteTemplate(final NoteTemplate note){
        return noteTemplateRepository.save(note);
    }
}
