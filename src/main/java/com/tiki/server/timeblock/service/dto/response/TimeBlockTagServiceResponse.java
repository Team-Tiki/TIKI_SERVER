package com.tiki.server.timeblock.service.dto.response;

import com.tiki.server.timeblock.entity.TimeBlock;

import jakarta.validation.constraints.NotNull;

public record TimeBlockTagServiceResponse(
        @NotNull long id,
        @NotNull String name,
        @NotNull String color
) {

    public static TimeBlockTagServiceResponse from(final TimeBlock timeBlock){
        return new TimeBlockTagServiceResponse(timeBlock.getId(), timeBlock.getName(), timeBlock.getColor());
    }
}