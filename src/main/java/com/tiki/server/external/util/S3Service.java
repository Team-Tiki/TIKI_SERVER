package com.tiki.server.external.util;

import static com.tiki.server.external.constant.ExternalConstant.FILE_SAVE_PREFIX;
import static com.tiki.server.external.constant.ExternalConstant.PRE_SIGNED_URL_EXPIRE_MINUTE;
import static com.tiki.server.external.message.ErrorCode.*;
import static com.tiki.server.timeblock.constant.TimeBlockConstant.FILE_DELIMITER;

import java.time.Duration;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.tiki.server.external.config.AWSConfig;
import com.tiki.server.external.dto.request.S3DeleteRequest;
import com.tiki.server.external.dto.response.PreSignedUrlResponse;
import com.tiki.server.external.exception.ExternalException;

import lombok.RequiredArgsConstructor;
import lombok.val;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

@Component
@RequiredArgsConstructor
public class S3Service {

	private final AWSConfig awsConfig;

	@Value("${aws-property.bucket}")
	private String bucket;

	public PreSignedUrlResponse getUploadPreSignedUrl(String fileFormat) {
		try {
			String fileName = generateFileName(fileFormat);
			String key = FILE_SAVE_PREFIX + fileName;
			S3Presigner preSigner = awsConfig.getS3PreSigner();
			PutObjectRequest putObjectRequest = createPutObjectRequest(key);
			PutObjectPresignRequest putObjectPresignRequest = createPutObjectPresignRequest(putObjectRequest);
			String url = preSigner.presignPutObject(putObjectPresignRequest).url().toString();
			return PreSignedUrlResponse.of(fileName, url);
		} catch (RuntimeException e) {
			throw new ExternalException(PRESIGNED_URL_GET_ERROR);
		}
	}

	public void deleteFile(S3DeleteRequest request) {
		try {
			S3Client s3Client = awsConfig.getS3Client();
			s3Client.deleteObject((DeleteObjectRequest.Builder builder) ->
				builder.bucket(bucket)
					.key(request.fileName())
					.build()
			);
		} catch (RuntimeException e) {
			throw new ExternalException(FILE_DELETE_ERROR);
		}
	}

	private PutObjectRequest createPutObjectRequest(String key) {
		return PutObjectRequest.builder()
			.bucket(bucket)
			.key(key)
			.build();
	}

	private PutObjectPresignRequest createPutObjectPresignRequest(PutObjectRequest putObjectRequest) {
		return PutObjectPresignRequest.builder()
			.signatureDuration(Duration.ofMinutes(PRE_SIGNED_URL_EXPIRE_MINUTE))
			.putObjectRequest(putObjectRequest)
			.build();
	}

	private String generateFileName(String fileFormat) {
		return UUID.randomUUID() + FILE_DELIMITER + fileFormat;
	}
}
