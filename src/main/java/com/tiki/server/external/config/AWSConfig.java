package com.tiki.server.external.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.auth.credentials.SystemPropertyCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
@RequiredArgsConstructor
public class AWSConfig {

	private static final String AWS_ACCESS_KEY_ID = "aws.accessKeyId";
	private static final String AWS_SECRET_ACCESS_KEY = "aws.secretAccessKey";

	@Value("${aws-property.access-key}")
	private String accessKey;

	@Value("${aws-property.secret-key}")
	private String secretKey;

	@Value("${aws-property.aws-region}")
	private String region;

	@Bean
	public SystemPropertyCredentialsProvider systemPropertyCredentialsProvider() {
		System.setProperty(AWS_ACCESS_KEY_ID, accessKey);
		System.setProperty(AWS_SECRET_ACCESS_KEY, secretKey);
		return SystemPropertyCredentialsProvider.create();
	}

	@Bean
	public S3Client getS3Client() {
		return S3Client.builder()
			.region(getRegion())
			.credentialsProvider(systemPropertyCredentialsProvider())
			.build();
	}

	@Bean
	public S3Presigner getS3PreSigner() {
		return S3Presigner.builder()
			.region(getRegion())
			.credentialsProvider(systemPropertyCredentialsProvider())
			.build();
	}

	private Region getRegion() {
		return Region.of(region);
	}
}
