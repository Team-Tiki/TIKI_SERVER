package com.tiki.server.document.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.time.LocalDate;

import com.tiki.server.common.entity.BaseTime;

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
public class DeletedDocument extends BaseTime {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "deleted_document_id")
	private Long id;

	private String fileName;

	private String fileUrl;

	private long teamId;

	private double capacity;

	@Builder
	public static DeletedDocument of(final String fileName, final String fileUrl, final long teamId,
			final double capacity) {
		return DeletedDocument.builder()
			.fileName(fileName)
			.fileUrl(fileUrl)
			.teamId(teamId)
			.capacity(capacity)
			.build();
	}
}
