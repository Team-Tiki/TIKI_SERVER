package com.tiki.server.external.util;

import static com.tiki.server.external.constant.ExternalConstant.FILE_SAVE_PREFIX;
import static com.tiki.server.external.constant.ExternalConstant.PRE_SIGNED_URL_EXPIRE_MINUTE;
import static com.tiki.server.external.message.ErrorCode.*;
import static com.tiki.server.external.constant.ExternalConstant.FILE_DELIMITER;

import java.time.Duration;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.tiki.server.external.config.AWSConfig;
import com.tiki.server.external.dto.response.GetObjectPreSignedUrlResponse;
import com.tiki.server.external.dto.response.PutObjectPreSignedUrlResponse;
import com.tiki.server.external.exception.ExternalException;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

@Component
@RequiredArgsConstructor
public class AwsHandler {

	private final AWSConfig awsConfig;

	@Value("${aws-property.bucket}")
	private String bucket;

	public PutObjectPreSignedUrlResponse getUploadPreSignedUrl(final String fileFormat) {
		try {
			String fileName = generateFileName(fileFormat);
			String key = FILE_SAVE_PREFIX + fileName;
			S3Presigner preSigner = awsConfig.getS3PreSigner();
			PutObjectRequest putObjectRequest = createPutObjectRequest(key);
			PutObjectPresignRequest putObjectPresignRequest = createPutObjectPresignRequest(putObjectRequest);
			String url = preSigner.presignPutObject(putObjectPresignRequest).url().toString();
			return PutObjectPreSignedUrlResponse.of(fileName, url);
		} catch (RuntimeException e) {
			throw new ExternalException(PRESIGNED_URL_GET_ERROR);
		}
	}

	public GetObjectPreSignedUrlResponse getDownloadPreSignedUrl(final String fileKey) {
		try {
			S3Presigner preSigner = awsConfig.getS3PreSigner();
			GetObjectRequest getObjectRequest = createGetObjectRequest(fileKey);
			GetObjectPresignRequest getObjectPresignRequest = createGetObjectPresignRequest(getObjectRequest);
			String url = preSigner.presignGetObject(getObjectPresignRequest).url().toString();
			return GetObjectPreSignedUrlResponse.from(url);
		} catch (RuntimeException e) {
			throw new ExternalException(PRESIGNED_URL_GET_ERROR);
		}
	}

	public void deleteFile(final String request) {
		try {
			S3Client s3Client = awsConfig.getS3Client();
			s3Client.deleteObject((DeleteObjectRequest.Builder builder) ->
				builder.bucket(bucket)
					.key(request)
					.build()
			);
		} catch (RuntimeException e) {
			throw new ExternalException(FILE_DELETE_ERROR);
		}
	}

	private PutObjectRequest createPutObjectRequest(final String key) {
		return PutObjectRequest.builder()
			.bucket(bucket)
			.key(key)
			.build();
	}

	private PutObjectPresignRequest createPutObjectPresignRequest(final PutObjectRequest putObjectRequest) {
		return PutObjectPresignRequest.builder()
			.signatureDuration(Duration.ofMinutes(PRE_SIGNED_URL_EXPIRE_MINUTE))
			.putObjectRequest(putObjectRequest)
			.build();
	}

	private GetObjectRequest createGetObjectRequest(final String key) {
		return GetObjectRequest.builder()
			.bucket(bucket)
			.key(key)
			.build();
	}

	private GetObjectPresignRequest createGetObjectPresignRequest(final GetObjectRequest getObjectRequest) {
		return GetObjectPresignRequest.builder()
			.signatureDuration(Duration.ofMinutes(PRE_SIGNED_URL_EXPIRE_MINUTE))
			.getObjectRequest(getObjectRequest)
			.build();
	}

	private String generateFileName(final String fileFormat) {
		return UUID.randomUUID() + FILE_DELIMITER + fileFormat;
	}
}
