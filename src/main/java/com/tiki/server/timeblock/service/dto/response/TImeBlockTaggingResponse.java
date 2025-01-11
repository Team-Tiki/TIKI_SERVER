package com.tiki.server.timeblock.service.dto.response;

import com.tiki.server.timeblock.entity.BlockType;
import com.tiki.server.timeblock.entity.TimeBlock;
import java.time.LocalDate;

public record TImeBlockTaggingResponse(
        long timeBlockId,
        String name,
        BlockType type,
        String color,
        LocalDate startDate
) {
    public static TImeBlockTaggingResponse from(final TimeBlock timeBlock) {
        return new TImeBlockTaggingResponse(
                timeBlock.getId(),
                timeBlock.getName(),
                timeBlock.getType(),
                timeBlock.getColor(),
                timeBlock.getStartDate()
        );
    }
}