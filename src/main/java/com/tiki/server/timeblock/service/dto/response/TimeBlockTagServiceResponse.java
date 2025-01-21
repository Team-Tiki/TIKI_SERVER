package com.tiki.server.timeblock.service.dto.response;

import com.tiki.server.timeblock.entity.BlockType;
import com.tiki.server.timeblock.entity.TimeBlock;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TimeBlockTagServiceResponse(
        @NotNull long id,
        @NotNull String name,
        @NotNull String color,
        @NotNull BlockType blockType,
        @NotNull LocalDate startDate
) {

    public static TimeBlockTagServiceResponse from(final TimeBlock timeBlock) {
        return new TimeBlockTagServiceResponse(
                timeBlock.getId(),
                timeBlock.getName(),
                timeBlock.getColor(),
                timeBlock.getType(),
                timeBlock.getStartDate()
        );
    }
}