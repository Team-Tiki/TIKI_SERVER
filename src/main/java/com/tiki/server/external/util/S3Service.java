package com.tiki.server.external.util;

import static com.tiki.server.external.message.ErrorCode.*;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.tiki.server.external.config.AWSConfig;
import com.tiki.server.external.exception.ExternalException;
import com.tiki.server.external.message.ErrorCode;

import lombok.RequiredArgsConstructor;
import lombok.val;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Component
@RequiredArgsConstructor
public class S3Service {

	private static final Long MAX_FILE_SIZE = 30 * 1024 * 1024L;
	private static final String FILE_NAME_START_POINT = ".";
	private static final String CONTENT_DISPOSITION = "inline";
	private static final String DELIMITER = "/";

	private final AWSConfig awsConfig;

	@Value("${aws-property.bucket}")
	private String bucket;

	@Value("${aws-property.s3-url}")
	private String s3URL;

	public String uploadFile(String directoryPath, MultipartFile file) {
		validateFileSize(file);
		try {
			val key = directoryPath + DELIMITER + generateFileName(file);
			val s3Client = awsConfig.getS3Client();
			val request = createRequest(key, file.getContentType());
			val requestBody = RequestBody.fromBytes(file.getBytes());
			s3Client.putObject(request, requestBody);
			return s3URL + key;
		} catch (IOException exception) {
			throw new IllegalArgumentException();
		}
	}

	public void deleteFile(String key) throws IOException {
		val s3Client = awsConfig.getS3Client();
		s3Client.deleteObject((DeleteObjectRequest.Builder builder) ->
			builder.bucket(bucket)
				.key(key)
				.build()
		);
	}

	private PutObjectRequest createRequest(String key, String contentType) {
		return PutObjectRequest.builder()
			.bucket(bucket)
			.key(key)
			.contentType(contentType)
			.contentDisposition(CONTENT_DISPOSITION)
			.build();
	}

	private String generateFileName(MultipartFile file) {
		return UUID.randomUUID() + getFileFormat(file.getName());
	}

	private String getFileFormat(String fileName) {
		return fileName.substring(fileName.lastIndexOf(FILE_NAME_START_POINT));
	}

	private void validateFileSize(MultipartFile file) {
		if (file.getSize() > MAX_FILE_SIZE) {
			throw new ExternalException(INVALID_FILE_SIZE);
		}
	}
}
