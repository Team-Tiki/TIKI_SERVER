package com.tiki.server.timeblock.service.dto.response;

import com.tiki.server.timeblock.entity.TimeBlock;
import java.util.List;

public record AllTimeBlockServiceResponse(
        List<TImeBlockTaggingResponse> tImeBlockTaggingResponses
) {
    public static AllTimeBlockServiceResponse from(List<TimeBlock> timeBlocks) {
        return new AllTimeBlockServiceResponse(timeBlocks.stream().map(TImeBlockTaggingResponse::from).toList());
    }
}