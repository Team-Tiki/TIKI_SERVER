package com.tiki.server.document.entity;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import com.tiki.server.common.entity.BaseTime;
import com.tiki.server.timeblock.entity.TimeBlock;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Document extends BaseTime {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "document_id")
	private Long id;

	private String fileUrl;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "block_id")
	private TimeBlock timeBlock;

	@Builder
	public static Document of(String fileUrl, TimeBlock timeBlock) {
		return Document.builder()
			.fileUrl(fileUrl)
			.timeBlock(timeBlock)
			.build();
	}
}
