package com.tiki.server.document.entity;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

import com.tiki.server.common.entity.BaseTime;
import com.tiki.server.document.dto.request.DocumentCreateRequest;
import com.tiki.server.timeblock.entity.TimeBlock;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
public class Document extends BaseTime {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "document_id")
	private Long id;

	private String fileName;

	private String fileUrl;

	private String fileKey;

	private double capacity;

	private long teamId;

	private Long folderId;

	public static Document of(final DocumentCreateRequest request, final long teamId, final Long folderId) {
		return Document.builder()
			.fileName(request.fileName())
			.fileUrl(request.fileUrl())
			.capacity(request.capacity())
			.fileKey(request.fileKey())
			.teamId(teamId)
			.folderId(folderId)
			.build();
	}

	public static Document restore(final String fileName, final String fileUrl,
			final double capacity, final String fileKey, final long teamId) {
		return Document.builder()
				.fileName(fileName)
				.fileUrl(fileUrl)
				.capacity(capacity)
				.fileKey(fileKey)
				.teamId(teamId)
				.folderId(null)
				.build();
	}
}
