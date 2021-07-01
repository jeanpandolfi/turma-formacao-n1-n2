package com.jeanpandolfi.documentoservice.service;

import com.jeanpandolfi.documentoservice.config.ApplicationProperties;
import com.jeanpandolfi.documentoservice.service.dto.DocumentoDTO;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.UploadObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentoService {

    private final MinioClient minioClient;
    private final ApplicationProperties properties;

    @SneakyThrows
    public void salvar(DocumentoDTO dto){
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(properties.getMinio().getBucketName())
                        .object(dto.getUuid())
                        .stream(new ByteArrayInputStream(dto.getFile().getBytes()), dto.getFile().getBytes().length, 0)
                        .build());

        log.info("Arquivo salvo com sucesso");
    }


    @SneakyThrows
    public DocumentoDTO buscar(String uuid) {
        InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(properties.getMinio().getBucketName())
                        .object(uuid)
                        .build());
        return new DocumentoDTO(uuid, IOUtils.toString(stream));
    }

    @SneakyThrows
    public void deletar(String uuid){
        minioClient.removeObject(RemoveObjectArgs.builder()
                .bucket(properties.getMinio().getBucketName())
                .object(uuid)
                .build());
        log.info("Arquivo deletado com sucesso");
    }

}
