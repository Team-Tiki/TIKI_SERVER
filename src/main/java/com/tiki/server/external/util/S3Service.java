package com.tiki.server.external.util;

import static com.tiki.server.external.constant.ExternalConstant.FILE_SAVE_PREFIX;
import static com.tiki.server.external.constant.ExternalConstant.PRE_SIGNED_URL_EXPIRE_MINUTE;
import static com.tiki.server.external.message.ErrorCode.*;

import java.io.IOException;
import java.time.Duration;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.tiki.server.external.config.AWSConfig;
import com.tiki.server.external.dto.request.PreSignedUrlRequest;
import com.tiki.server.external.dto.response.PreSignedUrlResponse;
import com.tiki.server.external.exception.ExternalException;
import com.tiki.server.external.message.ErrorCode;

import lombok.RequiredArgsConstructor;
import lombok.val;
import software.amazon.awssdk.core.sync.RequestBody;
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

	@Value("${aws-property.s3-url}")
	private String s3URL;

	public PreSignedUrlResponse getUploadPreSignedUrl(PreSignedUrlRequest request) {
		try {
			val fileName = generateFileName(request.fileFormat());
			val key = FILE_SAVE_PREFIX + fileName;
			val preSigner = awsConfig.getS3PreSigner();
			val putObjectRequest = createPutObjectRequest(key);
			val putObjectPresignRequest = createPutObjectPresignRequest(putObjectRequest);
			val url = preSigner.presignPutObject(putObjectPresignRequest).url().toString();
			return PreSignedUrlResponse.of(fileName, url);
		} catch (RuntimeException e) {
			throw new ExternalException(PRESIGNED_URL_GET_ERROR);
		}
	}

	public void deleteFile(String key) throws IOException {
		try {
			val s3Client = awsConfig.getS3Client();
			s3Client.deleteObject((DeleteObjectRequest.Builder builder) ->
				builder.bucket(bucket)
					.key(key)
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
		return UUID.randomUUID() + fileFormat;
	}
}
