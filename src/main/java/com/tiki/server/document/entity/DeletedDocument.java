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

	private String fileName;

	private String fileUrl;

	private long teamId;

	private double capacity;

	private LocalDate deletedDate;

	@Builder
	public static DeletedDocument of(final String fileName, final String fileUrl, final long teamId,
			final double capacity, final LocalDate deletedDate) {
		return DeletedDocument.builder()
			.fileName(fileName)
			.fileUrl(fileUrl)
			.teamId(teamId)
			.capacity(capacity)
			.deletedDate(deletedDate)
			.build();
	}
}
