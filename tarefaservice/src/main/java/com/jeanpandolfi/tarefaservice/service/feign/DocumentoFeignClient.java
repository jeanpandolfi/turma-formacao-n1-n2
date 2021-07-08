package com.jeanpandolfi.tarefaservice.service.feign;

import com.jeanpandolfi.tarefaservice.service.dto.DocumentoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@FeignClient(name = "documentoservice", url = "${application.feign.documento}")
public interface DocumentoFeignClient {

    @PostMapping(value = "/api/documento", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<String> upload(MultipartFile file);

    @GetMapping("/api/documento/{uuid}")
    ResponseEntity<InputStream> obterPorUuid(@PathVariable("uuid") String uuid);

    @DeleteMapping("/api/documento/{uuid}")
    ResponseEntity<Void> deletar(@PathVariable("uuid") String uuid);
}
