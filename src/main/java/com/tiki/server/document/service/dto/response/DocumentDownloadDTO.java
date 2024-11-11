package com.tiki.server.document.service.dto.response;

import com.tiki.server.document.entity.Document;

public record DocumentDownloadDTO(
        long id,
        String fileName,
        String fileUrl,
        double capacity
) {
    public static DocumentDownloadDTO from(final Document document) {
        return new DocumentDownloadDTO(
                document.getId(),
                document.getFileName(),
                document.getFileUrl(),
                document.getCapacity());
    }
}