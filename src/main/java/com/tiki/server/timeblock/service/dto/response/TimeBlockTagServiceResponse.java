package com.tiki.server.timeblock.service.dto.response;

import com.tiki.server.timeblock.entity.TimeBlock;

public record TimeBlockTagServiceResponse(
        long id,
        String name,
        String color
) {

    public static TimeBlockTagServiceResponse from(final TimeBlock timeBlock){
        return new TimeBlockTagServiceResponse(timeBlock.getId(), timeBlock.getName(), timeBlock.getColor());
    }
}