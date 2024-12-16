package com.tiki.server.external.service;

import com.tiki.server.external.dto.request.S3DeleteRequest;
import com.tiki.server.external.dto.response.PreSignedUrlResponse;
import com.tiki.server.external.util.S3Handler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Handler s3Handler;

    public PreSignedUrlResponse getUploadPreSignedUrl(final String fileFormat) {
        return s3Handler.getUploadPreSignedUrl(fileFormat);
    }

    public void deleteFile(final S3DeleteRequest request) {
        s3Handler.deleteFile(request.fileKey());
    }
}
