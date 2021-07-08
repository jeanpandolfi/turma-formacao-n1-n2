package com.jeanpandolfi.documentoservice.service;

import com.jeanpandolfi.documentoservice.config.ApplicationProperties;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentoService {

    private final MinioClient minioClient;
    private final ApplicationProperties properties;

    @SneakyThrows
    public String salvar(MultipartFile file){
        String chave = String.format("%s-%s", UUID.randomUUID(), file.getOriginalFilename());
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(properties.getMinio().getBucketName())
                        .object(chave)
                        .stream(file.getInputStream(), -1, 1_048_576 * 20)
                        .build());

        log.info("Arquivo salvo com sucesso {}", chave);
        return chave;
    }


    @SneakyThrows
    public InputStream buscar(String uuid) {
        return minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(properties.getMinio().getBucketName())
                        .object(uuid)
                        .build());
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
