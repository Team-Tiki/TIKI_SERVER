package com.tiki.server.document.scheduler;

import static com.tiki.server.document.constants.DocumentConstants.TRASH_RETENTION_DAYS;

import java.time.LocalDate;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tiki.server.document.adapter.DeletedDocumentAdapter;
import com.tiki.server.document.entity.DeletedDocument;
import com.tiki.server.external.util.AwsHandler;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeletedDocumentTrashScheduler {

	private final DeletedDocumentAdapter deletedDocumentAdapter;
	private final AwsHandler awsHandler;

	@Scheduled(cron = "0 10 0 * * ?")
	public void clearDeletedDocuments() {
		LocalDate targetDate = LocalDate.now().minusDays(TRASH_RETENTION_DAYS);
		List<DeletedDocument> documents = deletedDocumentAdapter.getExpiredDeletedDocuments(targetDate);
		documents.forEach(document -> awsHandler.deleteFile(document.getFileKey()));
		deletedDocumentAdapter.deleteAll(documents);
	}
}
