package com.jeanpandolfi.documentoservice.config;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Configuration
@RequiredArgsConstructor
public class MinioConfiguration {

    private final ApplicationProperties applicationProperties;

    @Bean
    public MinioClient minioClient() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        MinioProperties minio = applicationProperties.getMinio();
        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint(minio.getUrl())
                        .credentials(minio.getAccessKey(), minio.getSecretKey())
                        .build();

        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(minio.getBucketName()).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(minio.getBucketName()).build());
        }
        return minioClient;
    }
}
