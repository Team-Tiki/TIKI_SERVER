package com.tiki.server.document.service.dto.response;

import com.tiki.server.document.entity.Document;

public record DocumentTagServiceResponse(
        long id,
        String fileName,
        String fileUrl,
        double capacity
) {

    public static DocumentTagServiceResponse from(final Document document) {
        return new DocumentTagServiceResponse(
                document.getId(),
                document.getFileName(),
                document.getFileUrl(),
                document.getCapacity());
    }
}