package com.tiki.server.timeblock.service.dto.response;

import com.tiki.server.timeblock.entity.TimeBlock;

public record TimeBlockNameDTO(
        long id,
        String name,
        String color
) {

    public static TimeBlockNameDTO from(final TimeBlock timeBlock){
        return new TimeBlockNameDTO(timeBlock.getId(), timeBlock.getName(), timeBlock.getColor());
    }
}