package com.tiki.server.document.service.dto.response;

import com.tiki.server.document.entity.Document;

public record DocumentTagGetServiceResponse(
        long id,
        String fileName,
        String fileUrl,
        double capacity
) {

    public static DocumentTagGetServiceResponse from(final Document document) {
        return new DocumentTagGetServiceResponse(
                document.getId(),
                document.getFileName(),
                document.getFileUrl(),
                document.getCapacity());
    }
}