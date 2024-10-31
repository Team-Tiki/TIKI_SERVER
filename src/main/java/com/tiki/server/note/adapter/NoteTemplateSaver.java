package com.tiki.server.note.adapter;

import com.tiki.server.note.entity.NoteTemplate;
import com.tiki.server.note.repository.NoteTemplateRepository;

public class NoteTemplateSaver {

    NoteTemplateRepository noteTemplateRepository;

    public NoteTemplate createNoteTemplate(NoteTemplate note){
        return noteTemplateRepository.save(note);
    }
}
