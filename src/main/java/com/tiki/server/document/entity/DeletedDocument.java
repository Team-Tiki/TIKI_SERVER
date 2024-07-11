package com.tiki.server.document.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class DeletedDocument {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "deleted_document_id")
	private Long id;

	private String fileUrl;

	@Column(name = "block_id")
	private long timeBlockId;

	private LocalDate deletedDate;

	@Builder
	public static DeletedDocument of(String fileUrl, long timeBlockId, LocalDate deletedDate) {
		return DeletedDocument.builder()
			.fileUrl(fileUrl)
			.timeBlockId(timeBlockId)
			.deletedDate(deletedDate)
			.build();
	}
}
