package com.tiki.server.documenttimeblockmanager.entity;

import static com.tiki.server.timeblock.message.ErrorCode.INVALID_DOCUMENT_TAG;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

import com.tiki.server.common.entity.BaseTime;
import com.tiki.server.timeblock.entity.TimeBlock;
import com.tiki.server.timeblock.exception.TimeBlockException;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
@Table(name = "document_time_block_manager")
public class DTBManager extends BaseTime {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private long documentId;

	private long timeBlockId;

	public static DTBManager of(final TimeBlock timeBlock, final long documentId) {
		return DTBManager.builder()
			.documentId(documentId)
			.timeBlockId(timeBlock.getId())
			.build();
	}

	public void validateTimeBlock(final TimeBlock timeBlock) {
		if (this.timeBlockId != timeBlock.getId()) {
			throw new TimeBlockException(INVALID_DOCUMENT_TAG);
		}
	}
}
