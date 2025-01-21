package com.tiki.server.external.service;

import com.tiki.server.external.dto.request.S3DeleteRequest;
import com.tiki.server.external.dto.response.PreSignedUrlResponse;
import com.tiki.server.external.util.AwsHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileHandlerService {

    private final AwsHandler awsHandler;

    public PreSignedUrlResponse getUploadPreSignedUrl(final String fileFormat) {
        return awsHandler.getUploadPreSignedUrl(fileFormat);
    }

    public void deleteFile(final S3DeleteRequest request) {
        awsHandler.deleteFile(request.fileKey());
    }
}
