package com.tiki.server.document.entity;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

import com.tiki.server.common.entity.BaseTime;
import com.tiki.server.document.dto.request.DocumentCreateRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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

	@Column(nullable = false)
	private String fileName;

	@Column(nullable = false)
	private String fileUrl;

	@Column(nullable = false)
	private String fileKey;

	@Column(nullable = false)
	private long capacity;

	@Column(nullable = false)
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

	public static Document restore(final DeletedDocument deletedDocument) {
		return Document.builder()
				.fileName(deletedDocument.getFileName())
				.fileUrl(deletedDocument.getFileUrl())
				.capacity(deletedDocument.getCapacity())
				.fileKey(deletedDocument.getFileKey())
				.teamId(deletedDocument.getTeamId())
				.folderId(null)
				.build();
	}
}
