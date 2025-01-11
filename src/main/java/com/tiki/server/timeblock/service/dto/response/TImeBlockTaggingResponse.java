package com.tiki.server.timeblock.service.dto.response;

import com.tiki.server.timeblock.entity.BlockType;
import com.tiki.server.timeblock.entity.TimeBlock;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record TImeBlockTaggingResponse(
        @NotNull long timeBlockId,
        @NotNull String name,
        @NotNull BlockType type,
        @NotNull String color,
        @NotNull LocalDate startDate
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