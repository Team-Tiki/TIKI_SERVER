package com.tiki.server.document.entity;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

import com.tiki.server.common.entity.BaseTime;
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

	private double capacity;

	private Long folderId;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "block_id")
	private TimeBlock timeBlock;

	public static Document of(String fileName, String fileUrl, TimeBlock timeBlock) {
		return Document.builder()
			.fileName(fileName)
			.fileUrl(fileUrl)
			.timeBlock(timeBlock)
			.build();
	}
}
