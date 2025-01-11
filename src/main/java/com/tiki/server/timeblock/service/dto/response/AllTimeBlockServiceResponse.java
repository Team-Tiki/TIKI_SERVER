package com.tiki.server.timeblock.service.dto.response;

import com.tiki.server.timeblock.entity.TimeBlock;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record AllTimeBlockServiceResponse(
        @NotNull List<TImeBlockTaggingResponse> tImeBlockTaggingResponses
) {
    public static AllTimeBlockServiceResponse from(final List<TimeBlock> timeBlocks) {
        return new AllTimeBlockServiceResponse(timeBlocks.stream().map(TImeBlockTaggingResponse::from).toList());
    }
}