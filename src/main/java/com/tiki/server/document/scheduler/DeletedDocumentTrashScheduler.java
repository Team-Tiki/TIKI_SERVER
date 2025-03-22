package com.tiki.server.document.scheduler;

import org.springframework.stereotype.Component;

import com.tiki.server.document.adapter.DeletedDocumentAdapter;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeletedDocumentTrashScheduler {

	private final DeletedDocumentAdapter deletedDocumentAdapter;


}
