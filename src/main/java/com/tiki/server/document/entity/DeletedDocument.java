package com.tiki.server.document.entity;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

import com.tiki.server.common.entity.BaseTime;

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
@NoArgsConstructor(access = PROTECTED)
@Builder(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
public class DeletedDocument extends BaseTime {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "deleted_document_id")
	private Long id;

	@Column(nullable = false)
	private String fileName;

	@Column(nullable = false)
	private String fileUrl;

	@Column(nullable = false)
	private String fileKey;

	@Column(nullable = false)
	private long teamId;

	@Column(nullable = false)
	private long capacity;

	public static DeletedDocument of(final Document document) {
		return DeletedDocument.builder()
			.fileName(document.getFileName())
			.fileUrl(document.getFileUrl())
			.fileKey(document.getFileKey())
			.teamId(document.getTeamId())
			.capacity(document.getCapacity())
			.build();
	}
}
